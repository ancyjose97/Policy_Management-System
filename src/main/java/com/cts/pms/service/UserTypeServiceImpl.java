package com.cts.pms.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cts.pms.dto.UserTypeDto;
import com.cts.pms.entity.UserType;
import com.cts.pms.repository.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserTypeService{

	
	private ModelMapper modelMapper;
	
	private UserTypeRepository userTypeRepository;

	public UserTypeServiceImpl(ModelMapper modelMapper, UserTypeRepository userTypeRepository) {
		this.modelMapper = modelMapper;
		this.userTypeRepository = userTypeRepository;
	}
	
	public UserTypeDto createUserType(UserTypeDto userTypeDto) {
		
		UserType userType = modelMapper.map(userTypeDto, UserType.class);
		
		UserType newUserType =	userTypeRepository.save(userType);
		
		return modelMapper.map(newUserType, UserTypeDto.class);
	}
}
