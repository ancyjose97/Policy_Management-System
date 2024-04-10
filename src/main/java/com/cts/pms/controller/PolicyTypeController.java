package com.cts.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pms.dto.PolicyTypeDto;
import com.cts.pms.service.PolicyTypeService;

@RestController
@RequestMapping("/api/v1.0/policy-type")
public class PolicyTypeController {

	@Autowired
	private PolicyTypeService policyTypeService;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PolicyTypeDto> createPolicyType(@RequestBody PolicyTypeDto policyTypeDto) {
		
		return new ResponseEntity<PolicyTypeDto>(policyTypeService.createPolicyType(policyTypeDto),HttpStatus.CREATED);
	}
}
