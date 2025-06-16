package com.training.springsecurityOauth2.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.training.springsecurityOauth2.dao.Users;



public class UserDeatailsImpl implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4024533132323876793L;

	private Integer id;

	private String username;
		
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserDeatailsImpl(Integer id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDeatailsImpl build(Users user) {
		List<GrantedAuthority> autorityList =   user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
		return new UserDeatailsImpl(user.getId(), user.getUsername(), user.getPassword(), autorityList);
	} 
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getId() {
		return id;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null|| getClass()!=obj.getClass())
			return false;
		UserDeatailsImpl userimpl = (UserDeatailsImpl)obj;
	
		return Objects.equals(id, userimpl.id);
		
	}

	
	
}
