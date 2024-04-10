package com.cts.pms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pms.dto.PolicyDto;
import com.cts.pms.entity.Policy;
import com.cts.pms.service.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1.0/policy")
public class PolicyController {

	private PolicyService policyService;

	public PolicyController(PolicyService policyService) {
		this.policyService = policyService;
	}
	
	
//	@PostMapping("/register/policy-type/{id}")
//	public ResponseEntity<PolicyDto> registerPolicy(@RequestBody PolicyDto policyDto, @PathVariable("id") Long policyType) {
//	
//	return new ResponseEntity<PolicyDto>(policyService.registerPolicy(policyDto, policyType), HttpStatus.CREATED);
//	
//	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<PolicyDto> registerPolicy(@Valid @RequestBody PolicyDto policyDto) {
		
		return new ResponseEntity<PolicyDto>(policyService.registerPolicy(policyDto), HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/getall")
	public ResponseEntity<List<PolicyDto>> getAllPolicies() {
		
		return new ResponseEntity<List<PolicyDto>>(policyService.getAllPolicies(), HttpStatus.OK);
	}
	
	
	@GetMapping("/searches")
	public ResponseEntity<List<PolicyDto>> searchPolicies(@RequestParam(required = false) String query, @RequestParam(required = false) Integer value) {
		
		return new ResponseEntity<List<PolicyDto>>(policyService.searchPolicies(query,value),HttpStatus.OK);
		
	}
	
}
