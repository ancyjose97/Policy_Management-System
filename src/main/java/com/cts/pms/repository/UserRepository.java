package com.cts.pms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.pms.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
	
	
}
