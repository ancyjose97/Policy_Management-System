package com.cts.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.pms.entity.Policy;
import com.cts.pms.entity.PolicyType;

public interface PolicyRepository extends JpaRepository<Policy, Long>{

	
	
	@Query("SELECT p FROM Policy p WHERE " + "p.durationInYears = :value " + "OR p.companyName = :query " + "OR p.generatedPolicyId = :query " + "OR p.policyName = :query " + "OR p.policytype.policyTypeName = :query" )
	List<Policy> searchPolicies(String query, Integer value);
	
	
	@Query("SELECT p.generatedPolicyId FROM Policy p WHERE " + "p.policytype = :policytype " + "ORDER BY p.generatedPolicyId DESC LIMIT 1")
	String findByLastGeneratedId(PolicyType policytype);
	
}
