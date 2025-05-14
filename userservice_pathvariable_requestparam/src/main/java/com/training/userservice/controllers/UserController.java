package com.training.userservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.userservice.dao.User;

@RestController
public class UserController {

	List<User> userList = new ArrayList<User>();
	
	public UserController() {
		userList.add(new User(1, "vivek", "Hyd", "vivek@gmail.com"));
		userList.add(new User(2, "anand", "bang", "anand@gmail.com"));
		userList.add(new User(3, "monika", "chennai", "monika@gmail.com"));
		userList.add(new User(4, "rachel", "pune", "rachel@gmail.com"));
	}
	
	
	@RequestMapping("/greet")
	public String greet() {
		return "Hello There!";
	}
	
	@RequestMapping("/users")
	public List<User> getAllUser(){
		return userList;
	}
	
	@RequestMapping(value = "/user/{uid}" ,method = RequestMethod.GET)
	public User getUserById(@PathVariable Integer uid) {
		User user = null;
		for(User u:userList) {
			if(u.getUid()==uid) {
				user = u;
			}
		}
		return user;
	}
	
	@RequestMapping(value = "/user" ,method = RequestMethod.GET)
	public User getUserwithId(@RequestParam(defaultValue = "2") Integer uid) {
		User user = null;
		for(User u:userList) {
			if(u.getUid()==uid) {
				user = u;
			}
		}
		return user;
	}
	
	
}
