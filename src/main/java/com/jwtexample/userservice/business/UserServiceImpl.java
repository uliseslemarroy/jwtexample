package com.jwtexample.userservice.business;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtexample.userservice.crosscutting.errormanagement.ObjectNotFoundException;
import com.jwtexample.userservice.model.Role;
import com.jwtexample.userservice.model.User;
import com.jwtexample.userservice.persistence.RoleRepository;
import com.jwtexample.userservice.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author uliseslemarroy
 *
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;

	@Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
	
	@Override
	public User saveUser(User user) {
		log.info("Saving new user {} into the DB", user.getName());
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role {} into the DB", role.getName());
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(Long userId, Long roleId) {
		
		Optional<User> opUser = userRepository.findById(userId);
		Optional<Role> opRole = roleRepository.findById(roleId);
		User user;
		Role role;
		if(opUser.get() == null) throw new ObjectNotFoundException("There wasn't found any user with id: "+userId);
		else user = opUser.get();
		
		if(opRole.get() == null) throw new ObjectNotFoundException("There wasn't found any role with id: "+roleId);
		else role = opRole.get();
		
		log.info("Adding role {} into user with email {}", role.getName(), user.getEmail());
		user.getRole().add(role); 
		//It isn't necessary to call userRepository.save(user) method because of @Transactional annotation
	}

	@Override
	public User getUser(String email) {
		//Assuming the email is unique
		log.info("Fetching user with email {}", email);
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getUsers() {
		//TODO In a 'real world application' this kind of methods should be paginated
		log.info("Fetching users");
		return userRepository.findAll();
	}

	@Override
	public List<Role> getRoles() {
		log.info("Fetching roles");
		return roleRepository.findAll();
	}

}
