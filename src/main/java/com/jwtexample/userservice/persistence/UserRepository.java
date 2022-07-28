package com.jwtexample.userservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtexample.userservice.model.User;

/**
 * 
 * @author uliseslemarroy
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
}
