package com.jwtexample.userservice.provide;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jwtexample.userservice.business.UserService;
import com.jwtexample.userservice.crosscutting.errormanagement.ErrorManager;
import com.jwtexample.userservice.model.Role;
import com.jwtexample.userservice.model.User;

/**
 * 
 * @author uliseslemarroy
 *
 */
@RestController
@RequestMapping("/v1")
public class UserController {
	
	private final UserService userService;
	
	private final ErrorManager errorManager;
	
	@Autowired
	public UserController(UserService userService, ErrorManager errorManager) {
		this.userService = userService;
		this.errorManager = errorManager;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>>getUsers(){
		return ResponseEntity.ok().body(userService.getUsers());
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<Role>>getRoles(){
		return ResponseEntity.ok().body(userService.getRoles());
	}
	
	@PostMapping("/users")
	public ResponseEntity<User>saveUser(@RequestBody User user){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("v1/users").toUriString());
		return ResponseEntity.created(uri).body(userService.saveUser(user));
	}
	
	@PostMapping("/roles")
	public ResponseEntity<Role>saveUser(@RequestBody Role role){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("v1/roles").toUriString());
		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}
	
	@PostMapping("/users/{userId}/roles/{roleId}")
	public ResponseEntity<Role>addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId){
		try {
			userService.addRoleToUser(userId, roleId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new ResponseStatusException(errorManager.getHttpStatusForException(e), e.getMessage(), e);
		}
	}
	
	
}
