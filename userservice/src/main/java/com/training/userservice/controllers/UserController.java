package com.training.userservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.training.userservice.UserserviceApplication;
import com.training.userservice.dao.User;

@RestController
public class UserController {

    private final UserserviceApplication userserviceApplication;

	List<User> userList = new ArrayList<User>();
	
	public UserController(UserserviceApplication userserviceApplication) {
		userList.add(new User(1, "vivek", "Hyd", "vivek@gmail.com"));
		userList.add(new User(2, "anand", "bang", "anand@gmail.com"));
		userList.add(new User(3, "monika", "chennai", "monika@gmail.com"));
		userList.add(new User(4, "rachel", "pune", "rachel@gmail.com"));
		this.userserviceApplication = userserviceApplication;
	}
	
	
	@RequestMapping("/greet")
	public String greet() {
		return "Hello There!";
	}
	
	@GetMapping("/users")
	public List<User> getAllUser(){
		return userList;
	}
	
	@GetMapping("/user/{uid}")
	public User getUserById(@PathVariable Integer uid) {
		return userList.stream().filter(u->u.getUid()==uid)
				.findAny().orElseThrow(()-> new RuntimeException("User Not Found"));
		
	}
	
	@GetMapping("/user")
	public User getUserwithId(@RequestParam(defaultValue = "2") Integer uid) {
		return userList.stream().filter(u->u.getUid()==uid)
				.findAny().orElseThrow(()-> new RuntimeException("User Not Found"));
	}
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User u) {
		userList.add(u);
		return userList.stream().filter(x->x.getUid()==u.getUid())
				.findAny().orElseThrow(()-> new RuntimeException("Unable to save User"));
	}
	
	@PutMapping("/update/{uid}")
	public User updateUser(@PathVariable int uid,@RequestBody User user) {
		User existing = userList.stream().filter(u->u.getUid()==uid)
		.findAny().orElseThrow(()-> new RuntimeException("User Not Found"));
		
		existing.setUname(user.getUname());
		existing.setAddress(user.getAddress());
		existing.setContact(user.getContact());
		
		return existing;
		
	}
	
	@PatchMapping("/updateprop/{uid}")
	public User updateUserProp(@PathVariable int uid,@RequestBody User user) {
		User existing = userList.stream().filter(u->u.getUid()==uid)
		.findAny().orElseThrow(()-> new RuntimeException("User Not Found"));
		
		existing.setUname(user.getUname()!=null?user.getUname():existing.getUname());
		existing.setAddress(user.getAddress()!=null?user.getAddress():existing.getAddress());
		existing.setContact(user.getContact()!=null?user.getContact():existing.getContact());
		
		return existing;
		
	}
	
	@DeleteMapping("/delete/{uid}")
	public String deleteUser(@PathVariable Integer uid) {
		User existing = userList.stream().filter(u->u.getUid()==uid)
				.findAny().orElseThrow(()-> new RuntimeException("User Not Found"));
		
		userList.remove(existing);
		
		return "User Delted with uid "+uid;
		
	}
	
	
	
}
