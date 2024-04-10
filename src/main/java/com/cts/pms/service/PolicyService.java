package com.cts.pms.service;

import java.util.List;

import com.cts.pms.dto.PolicyDto;
import com.cts.pms.entity.Policy;

public interface PolicyService {

	
//	PolicyDto registerPolicy(PolicyDto policyDto, Long policyType);
	
	PolicyDto registerPolicy(PolicyDto policyDto);
	
	List<PolicyDto> getAllPolicies();
	
	List<PolicyDto> searchPolicies(String query,Integer value);
}
