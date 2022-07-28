package com.jwtexample.userservice.business;

import java.util.List;

import com.jwtexample.userservice.model.Role;
import com.jwtexample.userservice.model.User;

/**
 * 
 * @author uliseslemarroy
 *
 */
public interface UserService {
	
	User saveUser(User user);
	
	Role saveRole(Role role);
	
	void addRoleToUser(Long userId, Long roleId);
	
	User getUser(String email);
	
	List<User> getUsers();

	List<Role> getRoles();

}
