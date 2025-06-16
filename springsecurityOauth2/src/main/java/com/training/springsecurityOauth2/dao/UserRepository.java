package com.training.springsecurityOauth2.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer>{
	
	public Optional<Users> findByusername(String username);

}
