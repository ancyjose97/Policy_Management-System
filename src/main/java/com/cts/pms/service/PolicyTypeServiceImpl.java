package com.cts.pms.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pms.dto.PolicyTypeDto;
import com.cts.pms.entity.PolicyType;
import com.cts.pms.repository.PolicyTypeRepository;

@Service
public class PolicyTypeServiceImpl implements PolicyTypeService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PolicyTypeRepository policyTypeRepository;
	
	
	@Override
	public PolicyTypeDto createPolicyType(PolicyTypeDto policyTypeDto) {
	
	PolicyType policyType =	modelMapper.map(policyTypeDto, PolicyType.class);
	
	PolicyType newPolicyType =	policyTypeRepository.save(policyType);
		
		return modelMapper.map(newPolicyType, PolicyTypeDto.class);
	}

}
