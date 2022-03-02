package com.zensar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.entity.AuthenticateRequest;
import com.zensar.utils.JwtUtils;

@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;
	
	public static String jwtToken=null;
	

	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticateRequest request) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(request.getUsername());
		}

		jwtToken = jwtUtils.generateToken(request.getUsername());

		return new ResponseEntity<>(jwtToken, HttpStatus.OK);
	}
}
