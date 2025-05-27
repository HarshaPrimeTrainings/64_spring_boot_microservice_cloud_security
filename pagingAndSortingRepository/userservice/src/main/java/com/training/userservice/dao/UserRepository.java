package com.training.userservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> getByUname(String uname);
	
	@Query(value = "select * from user where address=:addr",nativeQuery = true)
	public List<User> searchUseraddr(String addr);

}
