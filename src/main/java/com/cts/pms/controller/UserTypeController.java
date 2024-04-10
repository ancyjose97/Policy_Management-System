package com.cts.pms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pms.dto.UserTypeDto;
import com.cts.pms.service.UserTypeService;

@RestController
@RequestMapping("api/v1.0/user-type")
public class UserTypeController {

	
	private UserTypeService userTypeService;

	public UserTypeController(UserTypeService userTypeService) {
		this.userTypeService = userTypeService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<UserTypeDto> createUserType(@RequestBody UserTypeDto userTypeDto) {
		
		return new ResponseEntity<UserTypeDto>(userTypeService.createUserType(userTypeDto), HttpStatus.CREATED);
		
	}
	
	
}
