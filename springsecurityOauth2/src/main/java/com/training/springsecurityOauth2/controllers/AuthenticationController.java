package com.training.springsecurityOauth2.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.springsecurityOauth2.dao.RoleRepository;
import com.training.springsecurityOauth2.dao.UserRepository;
import com.training.springsecurityOauth2.dto.JwtResponse;
import com.training.springsecurityOauth2.dto.LoginDto;
import com.training.springsecurityOauth2.dto.UserDto;
import com.training.springsecurityOauth2.dao.Role;
import com.training.springsecurityOauth2.dao.Roles;
import com.training.springsecurityOauth2.dao.Users;
import com.training.springsecurityOauth2.security.JwtUtils;
import com.training.springsecurityOauth2.service.UserDeatailsImpl;
import com.training.springsecurityOauth2.service.UserDetailsServiceImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@RestController
public class AuthenticationController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository rolerepo;
	
	@Autowired
	PasswordEncoder pwdencoder;
	
	@Autowired
	AuthenticationManager userAuthenticationManager;
	
	@Autowired
	JwtUtils jwtutils;
	
	@PostMapping("/signin")
	public ResponseEntity<Users> signin(@RequestBody UserDto user){
		user.setPassword(pwdencoder.encode(user.getPassword()));
		Set<Roles> roleSet = new HashSet<>();
		Set<String> payLoadRoles = user.getRoles(); 
		payLoadRoles.forEach(role->{
			Roles userRole= rolerepo.findByRole(Role.valueOf(role))
					.orElseThrow(()->new RuntimeException("ROLE NOT FOUND"));
			roleSet.add(userRole);
		});
		Users siginUser = new Users();
		siginUser.setUserId(user.getUserId());
		siginUser.setUsername(user.getUsername());
		siginUser.setPassword(user.getPassword());
		siginUser.setRoles(roleSet);
		return new ResponseEntity<Users>(userRepo.save(siginUser),HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginUser){
		Authentication authentication =  userAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtutils.generateJwtToken(authentication);
		
		UserDeatailsImpl useredetailsimpl = (UserDeatailsImpl)authentication.getPrincipal();
		List<String> roles =    useredetailsimpl.getAuthorities().stream().map(item->item.getAuthority()).collect(Collectors.toList());
		
		JwtResponse jwtResp =  new JwtResponse(jwt, useredetailsimpl.getId(), useredetailsimpl.getUsername(), roles);
		
		return new ResponseEntity<JwtResponse>(jwtResp,HttpStatus.OK);
		
		
	} 
}
