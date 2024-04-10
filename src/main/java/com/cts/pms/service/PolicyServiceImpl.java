package com.cts.pms.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cts.pms.dto.PolicyDto;
import com.cts.pms.entity.Policy;
import com.cts.pms.entity.PolicyType;
import com.cts.pms.entity.UserType;
import com.cts.pms.exception.PolicyApiException;
import com.cts.pms.exception.ResourceNotFoundException;
import com.cts.pms.repository.PolicyRepository;
import com.cts.pms.repository.PolicyTypeRepository;
import com.cts.pms.repository.UserTypeRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

	private PolicyRepository policyRepository;

	private PolicyTypeRepository policyTypeRepository;

	private ModelMapper modelMapper;
	
	private UserTypeRepository userTypeRepository;

	public PolicyServiceImpl(PolicyRepository policyRepository, ModelMapper modelMapper,
			PolicyTypeRepository policyTypeRepository, UserTypeRepository userTypeRepository) {
		this.policyRepository = policyRepository;
		this.modelMapper = modelMapper;
		this.policyTypeRepository = policyTypeRepository;
		this.userTypeRepository = userTypeRepository;
	}

	@Override
	public PolicyDto registerPolicy(PolicyDto policyDto) {

		PolicyType fetchPolicyType = policyTypeRepository.findById(policyDto.getPolicytypeId())
				.orElseThrow(() -> new ResourceNotFoundException("Policy Type", "id", policyDto.getPolicytypeId()));
		
	UserType fetchUserType = userTypeRepository.findById(policyDto.getUsertypeId()).orElseThrow(() -> new ResourceNotFoundException("User Type", "id", policyDto.getUsertypeId()));
		
	//	List<UserType> fetchUserTypes = userTypeRepository.findAllById(policyDto.getUsertypesId());

		Policy policy = new Policy();
		
		policy.setCompanyName(policyDto.getCompanyName());
		policy.setDurationInYears(policyDto.getDurationInYears());
		
	policy.setInitialDeposit(policyDto.getInitialDeposit());
	policy.setInterest(policyDto.getInterest());
	policy.setPolicyName(policyDto.getPolicyName());
	policy.setStartDate(policyDto.getStartDate());
	policy.setTermAmount(policyDto.getTermAmount());
	policy.setTermsPerYear(policyDto.getTermsPerYear());
	
	

	//	Policy policy = modelMapper.map(policyDto, Policy.class);

		policy.setPolicytype(fetchPolicyType);
		
	policy.setUsertype(fetchUserType);
		
	//	policy.setUsertypes(fetchUserTypes);

		// Generate new policy id

		String generatedPolicyId = generatePolicyId(policyDto, fetchPolicyType);
		
		policy.setGeneratedPolicyId(generatedPolicyId);

		policy.setEndDate(policyDto.getStartDate().plusYears(policyDto.getDurationInYears()));

		double maturityAmount = calculateMaturityAmount(policyDto);

		policy.setMaturityAmount(maturityAmount);

		Policy registeredPolicy = policyRepository.save(policy);
		

		return modelMapper.map(registeredPolicy, PolicyDto.class);
	}

	private double calculateMaturityAmount(PolicyDto policyDto) {

		double maturityAmount = (policyDto.getInitialDeposit())
				+ (policyDto.getDurationInYears() * policyDto.getTermsPerYear() * policyDto.getTermAmount()) +

				((policyDto.getDurationInYears() * policyDto.getTermsPerYear() * policyDto.getTermAmount())
						* (policyDto.getInterest() / 100));

		return maturityAmount;

	}

	// Generate new policy id

	public String generatePolicyId(PolicyDto policyDto, PolicyType fetchPolicyType) {

		String extractPrefix = extractPrefixOfId(fetchPolicyType);
		
		int policyStartDate = policyDto.getStartDate().getYear();
		
		String yearPrefix = String.valueOf(policyStartDate);
		

		String lastId = policyRepository.findByLastGeneratedId(fetchPolicyType);
		
		String newNumber;
	
		if(lastId == null) {
			
			newNumber = "001";
		}
		
		else {
			
			String extracted = lastId.substring(lastId.lastIndexOf("-") + 1); // "005"
			
			int value = Integer.parseInt(extracted) + 1; // 5 + 1 = 6
			
			
			newNumber = String.format("%03d", value); //006
		}
		
   
	String returnId = String.format("%s-%s-%s", extractPrefix, yearPrefix, newNumber);
		
		
		 

		return returnId;

	}

	public String extractPrefixOfId(PolicyType fetchPolicyType ) {
		
		String prefix = null;
		
		switch(fetchPolicyType.getPolicyTypeName()) {
		
		case "Vehicle Insurance": {
			
			prefix = "VI";
			break;
		}
			
case "Travel Insurance": {
			
			prefix = "TI";
			break;
		}
		
case "Health Insurance": {
	
	prefix = "HI";
	break;
		
		}

case "Child Plans": {
	
	prefix = "CP";
	break;
		
		}

case "Retirement Plans": {
	
	prefix = "RP";
	break;
		
		}

case "Life Insurance": {
	
	prefix = "LI";
	break;
		
		}

default:
	
	throw new IllegalArgumentException("No policy type found with fetched policy type name");
		
		
	}

		return prefix;
}
	
	
	public List<PolicyDto> getAllPolicies() {
		
		
		List<PolicyDto> getAllPolicies = policyRepository.findAll().stream().map((policy) -> modelMapper.map(policy, PolicyDto.class)).toList();
		
		if(getAllPolicies.isEmpty()) {
			
			throw new PolicyApiException("No policy registered in Database!");
		}
		
		return getAllPolicies;
	}
	
	
	public List<PolicyDto> searchPolicies(String query, Integer value) {
		
		List<PolicyDto> policies = policyRepository.searchPolicies(query,value).stream().map((policy) -> modelMapper.map(policy, PolicyDto.class)).toList();
		
		if(policies.isEmpty()) {
			
			throw new PolicyApiException("No policy is found based on your search criteria!");
		}
		
		return policies;
	
	
	}
	
}
