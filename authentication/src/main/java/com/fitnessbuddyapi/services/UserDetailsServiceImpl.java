package com.fitnessbuddyapi.services;

import com.fitnessbuddy.amqp.RabbitMQMessageProducer;
import com.fitnessbuddy.clients.fraud.FraudClient;
import com.fitnessbuddy.clients.fraud.response.FraudCheckResponse;
import com.fitnessbuddy.clients.notification.response.NotificationRequest;
import com.fitnessbuddyapi.dto.UserDTO;
import com.fitnessbuddyapi.models.ERole;
import com.fitnessbuddyapi.models.Role;
import com.fitnessbuddyapi.models.User;
import com.fitnessbuddyapi.payload.request.LoginRequest;
import com.fitnessbuddyapi.payload.request.SignupRequest;
import com.fitnessbuddyapi.payload.response.JwtResponse;
import com.fitnessbuddyapi.payload.response.MessageResponse;
import com.fitnessbuddyapi.repositories.RoleRepository;
import com.fitnessbuddyapi.security.jwt.HttpUtils;
import com.fitnessbuddyapi.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitnessbuddyapi.repositories.UserRepository;
import org.springframework.validation.Errors;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder encoder;

	private final AuthenticationManager authenticationManager;

	private final JwtUtils jwtUtils;

	private final FraudClient fraudClient;

	private final RabbitMQMessageProducer rabbitMQMessageProducer;


	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	public ResponseEntity<?> getAllUsersNames()
	{
		List<String> users= userRepository.findAllUsernames();
		if(users.isEmpty())
		{
			return  ResponseEntity.badRequest().body(new MessageResponse("No entry in the db"));
		}
		return ResponseEntity.ok(users);
	}


	public ResponseEntity<?> registerUserWithDefaultRole(SignupRequest signUpRequest, Errors error){
		if(error.hasErrors()) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(error.getFieldError().getField() + " " +error.getFieldError().getDefaultMessage()));   //create custom exception handler later
		}
		FraudCheckResponse fraudCheckResponse = fraudClient.isFraud(signUpRequest.getEmail(), HttpUtils.getClientIpAddressIfServletRequestExist());
		log.info("Ip address of person registering: " +HttpUtils.getClientIpAddressIfServletRequestExist());

		if(fraudCheckResponse.isFraudster())
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body(new MessageResponse("Your email or Ip address is(are) banned (CODE 403)"));
		}

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = new HashSet<>();

		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);

		user.setRoles(roles);
		userRepository.save(user);

		NotificationRequest notificationRequest = new NotificationRequest(
				user.getId(),
				user.getEmail(),
				String.format("Hi %s, welcome to Fitnessbuddy.",
						user.getUsername())
		);

		rabbitMQMessageProducer.publish(
				notificationRequest,
				"internal.exchange",
				"internal.notifications.routing-key"
		);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		FraudCheckResponse fraudCheckResponse = fraudClient.isFraud(userDetails.getEmail(), HttpUtils.getClientIpAddressIfServletRequestExist());

		if(fraudCheckResponse.isFraudster())
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body(new MessageResponse("Your email or Ip address is(are) banned (CODE 403). Please contact customer support for more details."));
		}

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}

}
