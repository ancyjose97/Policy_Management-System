package com.cts.pms.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.pms.dto.LoginDto;
import com.cts.pms.dto.RegisterDto;
import com.cts.pms.entity.Role;
import com.cts.pms.entity.User;
import com.cts.pms.exception.PolicyApiException;
import com.cts.pms.repository.RoleRepository;
import com.cts.pms.repository.UserRepository;
import com.cts.pms.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;
	
private AuthenticationManager authenticationManager;


private RoleRepository roleRepository;

private PasswordEncoder passwordEncoder;

private JwtTokenProvider jwtTokenProvider;

	

	public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
		RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		
	this.userRepository = userRepository;
	this.authenticationManager = authenticationManager;
	this.roleRepository = roleRepository;
	this.passwordEncoder = passwordEncoder;
	this.jwtTokenProvider = jwtTokenProvider;
}

	@Override
	public String registerCustomer(RegisterDto registerDto) {

		User user = new User();

		user.setFirstName(registerDto.getFirstName());

		user.setLastName(registerDto.getLastName());
		user.setDateOfBirth(registerDto.getDateOfBirth());
		user.setAddress(registerDto.getAddress());
		user.setContactNumber(registerDto.getContactNumber());
		user.setSalary(registerDto.getSalary());
		user.setPanNumber(registerDto.getPanNumber());

		if (userRepository.existsByEmail(registerDto.getEmail())) {

			throw new PolicyApiException("Email Id already exists!");
		} else {
			user.setEmail(registerDto.getEmail());
		}

		user.setEmployerType(registerDto.getEmployerType());

		if (registerDto.getEmployerType().equalsIgnoreCase("self-employed")) {

			user.setEmployer(null);

		}

		else if (registerDto.getEmployerType().equalsIgnoreCase("salaried")) {

			user.setEmployer(registerDto.getEmployer());

			if (registerDto.getEmployer() == null) {

				throw new PolicyApiException("Enter your employer information!");
			}
		}

		else {

			throw new IllegalArgumentException("Choose your employer type to be either self-employed or salaried");
		}
		
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		
	Role role = roleRepository.findByName("ROLE_USER").get();
	
	roles.add(role);
	
	user.setRoles(roles);

		userRepository.save(user);

		return "Customer registered successfully";
	}

	@Override
	public String login(LoginDto loginDto) {
	
		
	Authentication authentication =	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
	
	
	SecurityContextHolder.getContext().setAuthentication(authentication);
	
	String token = jwtTokenProvider.generateToken(authentication);
		
		return token;
	}

}
