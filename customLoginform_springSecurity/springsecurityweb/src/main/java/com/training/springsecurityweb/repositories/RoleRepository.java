package com.training.springsecurityweb.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.training.springsecurityweb.dao.Role;
import com.training.springsecurityweb.dao.Roles;

public interface RoleRepository extends CrudRepository<Roles, Integer>{

	public Optional<Roles> findByRole(Role role);
	
}
