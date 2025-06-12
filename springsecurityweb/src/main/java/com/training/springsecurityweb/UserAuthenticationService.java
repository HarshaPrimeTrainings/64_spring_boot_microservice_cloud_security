package com.training.springsecurityweb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.springsecurityweb.dao.UserInfo;
import com.training.springsecurityweb.repositories.UserRepository;

@Service
public class UserAuthenticationService implements UserDetailsService{

	@Autowired
	UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = repo.findByUname(username).orElseThrow(()->new RuntimeException(username+ " Not in DataBase"));
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		user.getRoles().stream().forEach(role->{
			authorities.add((GrantedAuthority)()->role.getRole().name());
		});
		
		return new User(user.getUname(), user.getPassword(), authorities);
	}

}
