package com.cts.pms.dto;

import java.util.Set;

import com.cts.pms.entity.Policy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyTypeDto {

	private Long id;
	private String policyTypeName;
	
}
