package com.jwtexample.userservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtexample.userservice.model.Role;
import com.jwtexample.userservice.model.User;

/**
 * 
 * @author uliseslemarroy
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
	
}
