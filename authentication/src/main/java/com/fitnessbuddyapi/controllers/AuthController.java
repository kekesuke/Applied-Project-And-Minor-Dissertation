package com.fitnessbuddyapi.controllers;

import javax.validation.Valid;

import com.fitnessbuddy.clients.fraud.FraudClient;
import com.fitnessbuddy.clients.fraud.requests.AddFraudsterRequest;
import com.fitnessbuddyapi.security.jwt.JwtUtils;
import com.fitnessbuddyapi.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.fitnessbuddyapi.payload.request.LoginRequest;
import com.fitnessbuddyapi.payload.request.SignupRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final UserDetailsServiceImpl userService;
	private final FraudClient fraudClient;
	private final JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return userService.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, Errors error) {
		return userService.registerUserWithDefaultRole(signUpRequest, error);
	}

	@PostMapping("/newFraudster")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> addFraudster(@RequestBody AddFraudsterRequest addFraudsterRequest){
		return fraudClient.addNewFraudster(addFraudsterRequest, jwtUtils.getUserNameForCurrentJWT());
	}

	@GetMapping("/fetchbymatch")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> getAllUsersWithUsername(){
		return userService.getAllUsersNames();
	}
}
