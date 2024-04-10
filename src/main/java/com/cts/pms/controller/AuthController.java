package com.cts.pms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pms.dto.JwtAuthResponse;
import com.cts.pms.dto.LoginDto;
import com.cts.pms.dto.RegisterDto;
import com.cts.pms.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/customer")
public class AuthController {

	
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@Valid  @RequestBody RegisterDto registerDto){
		
		return new ResponseEntity<String>(authService.registerCustomer(registerDto), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
		
		String token = authService.login(loginDto);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		
		jwtAuthResponse.setAccessToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
		
	}
}
