package com.training.springwithhibernatedemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.springwithhibernatedemo.User;
import com.training.springwithhibernatedemo.dao.UserDao;


@RestController
public class UserController {

	@Autowired
	UserDao dao;
	
	@PostMapping("/save")
	public String saveUser(@RequestBody User u) {
		return dao.saveUser(u);
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return dao.getAllUsers();
	}
	
	@GetMapping("/user/{uid}")
	public User getUserByid(@PathVariable Integer uid) {
		return dao.getUserByid(uid);
	}
	
	@DeleteMapping("/user/{uid}")
	public String deleteUser(@PathVariable Integer uid) {
		return dao.deleteUser(uid);
	}
	
	
	
}
