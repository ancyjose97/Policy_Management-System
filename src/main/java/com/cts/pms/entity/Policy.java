package com.cts.pms.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "policies")
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String policyName;
	
	private int durationInYears;
	
	private String companyName;
	
	private double initialDeposit;
	
	private int termsPerYear;
	
	private double termAmount;
	
	private double interest;
	
	
	private LocalDate startDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "policytype_id",nullable = false)
	private PolicyType policytype;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usertype_id",nullable = false)
	private UserType usertype;
	
	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "policies_user_types",
//				joinColumns = @JoinColumn(name = "policy_id", referencedColumnName = "id"),
//				inverseJoinColumns = @JoinColumn(name = "usertype_id", referencedColumnName = "id"))
//	
//	private List<UserType> usertypes = new ArrayList<>();

	
	
	private LocalDate endDate;
	
	private String generatedPolicyId;
	
	private double maturityAmount;
	
	
	
	
	
	
	
	
}
