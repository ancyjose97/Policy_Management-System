package com.cts.pms.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.pms.entity.User;
import com.cts.pms.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	
	private UserRepository userRepository;
	
	
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
	User user =	userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with the provided email " + email));
	
	Set<GrantedAuthority> authorities = user.getRoles()
												
											  .stream()
											  
											  .map((role) -> new SimpleGrantedAuthority(role.getName()))
											  
											  .collect(Collectors.toSet());
		
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);
	}

}
