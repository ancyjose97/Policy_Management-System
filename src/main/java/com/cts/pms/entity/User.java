package com.cts.pms.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.cts.pms.exception.PolicyApiException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
private String firstName;
	
	private String lastName;
	
	private LocalDate dateOfBirth;
	
	private String address;
	
	private Long contactNumber;
	
	
	private String email;
	
	private double salary;
	
	private String panNumber;
	
	private String employerType;
	
	private String employer;

	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "customers_roles",
				joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;
}
