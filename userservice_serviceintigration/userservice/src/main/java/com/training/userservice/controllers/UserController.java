package com.training.userservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.training.userservice.UserserviceApplication;
import com.training.userservice.dao.User;
import com.training.userservice.exception.UserNotFoundException;
import com.training.userservice.services.UserManageService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserManageService userService;
	
	@RequestMapping("/greet")
	public  ResponseEntity<String>  greet() {
		return ResponseEntity.status(HttpStatus.OK)
				.header("msg", "Hello")
				.body("Greetings to you");
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser(){
		HttpHeaders headers  = new HttpHeaders();
		headers.add("user-count", userService.getAllUser().size()+"");
		return new ResponseEntity<List<User>>(userService.getAllUser(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<User> getUserById(@PathVariable Integer uid) {
		User user =  userService.getUserById(uid);
		return ResponseEntity.status(HttpStatus.OK).header("user-count", userService.getAllUser()+"").body(user);
	}
	
	@GetMapping("/reqparam")
	public ResponseEntity<User> getUserwithId(@RequestParam(defaultValue = "2") Integer uid) {
		User user = userService.getUserById(uid);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User u) {
		return new ResponseEntity<User>(userService.saveUser(u), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{uid}")
	public ResponseEntity<User> updateUser(@PathVariable int uid,@RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(uid, user), HttpStatus.CREATED);
		
	}
	
	@PatchMapping("/updateprop/{uid}")
	public ResponseEntity<User> updateUserProp(@PathVariable int uid,@RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUserPartially(uid, user), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{uid}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer uid) {
		return new ResponseEntity<String>(userService.deleteUser(uid), HttpStatus.ACCEPTED);
		
	}
	
}
