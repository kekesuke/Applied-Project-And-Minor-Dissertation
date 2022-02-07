package com.fitnessbuddyapi.controllers;

import javax.validation.Valid;
import com.fitnessbuddyapi.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fitnessbuddyapi.payload.request.LoginRequest;
import com.fitnessbuddyapi.payload.request.SignupRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final UserDetailsServiceImpl userService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return userService.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, Errors error) {
		return userService.registerUserWithDefaultRole(signUpRequest, error);
	}
}
