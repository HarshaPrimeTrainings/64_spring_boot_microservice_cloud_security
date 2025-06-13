package com.training.springsecurityweb.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.springsecurityweb.dao.Role;
import com.training.springsecurityweb.dao.Roles;
import com.training.springsecurityweb.dao.UserInfo;
import com.training.springsecurityweb.repositories.RoleRepository;
import com.training.springsecurityweb.repositories.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepo;
	
	
	@PostMapping("/hello")
	public String greet(@RequestBody String username) {
		return "Hello "+username;
	}
	
	
	@PostMapping("/save")
	public UserInfo saveUser(@RequestBody UserPayload user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		UserInfo userInfo = new UserInfo(); 
		userInfo.setUname(user.getUname());
		userInfo.setEmail(user.getEmail());
		userInfo.setPassword(user.getPassword());
		
		
		Set<String> roles = user.getRoles();
		
		Set<Roles> userRoles = new HashSet<>();
		
		roles.forEach(role->{
			Roles userRole= roleRepo.findByRole(Role.valueOf(role.toString()))
					.orElseThrow(()->new RuntimeException("ROLE NOT FOUND"));
			userRoles.add(userRole);
		});
		userInfo.setRoles(userRoles);
		return userRepo.save(userInfo);
	}
	
}
