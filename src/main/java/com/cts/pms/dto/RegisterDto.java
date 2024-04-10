package com.cts.pms.dto;

import java.time.LocalDate;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

	private Long id;
	
private String firstName;
	
	private String lastName;
	
	private LocalDate dateOfBirth;
	
	private String address;
	
	private Long contactNumber;
	
	@Email(message = "Please provide valid email!")
	@NotBlank(message = "Email id should not be empty!")
	@NotEmpty(message = "Email id should not be null!")
	private String email;
	
	private double salary;
	
	private String panNumber;
	
	private String employerType;
	
	private String employer;
	
	@NotBlank(message = "Enter your password")
	@NotEmpty(message = "Enter your password")
	private String password;
	
	
}
