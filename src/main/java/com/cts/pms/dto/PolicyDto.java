package com.cts.pms.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cts.pms.entity.PolicyType;
import com.cts.pms.entity.UserType;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDto {

	private Long id;
private String policyName;
	
	

	private int durationInYears;
	
	private String companyName;
	
	private double initialDeposit;
	
	private int termsPerYear;
	
	private double termAmount;
	
	private double interest;
	
	@FutureOrPresent(message = "Policy start date should be on or after current date")
	private LocalDate startDate;

	private LocalDate endDate;
	
	private double maturityAmount = 0;
	
	private String generatedPolicyId = null;
	
	//Selecting policy type from available 6 types
	private Long policytypeId;
	
	//Selecting user type from available 5 types based on income
	private Long usertypeId;
	
	
//Selecting multiple user types from available 5 types
	//private List<Long> usertypesId = new ArrayList<>();
}
