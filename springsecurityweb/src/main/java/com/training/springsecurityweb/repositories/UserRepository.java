package com.training.springsecurityweb.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.training.springsecurityweb.dao.UserInfo;

public interface UserRepository extends CrudRepository<UserInfo, Integer>{

	public Optional<UserInfo> findByUname(String uname);
}
