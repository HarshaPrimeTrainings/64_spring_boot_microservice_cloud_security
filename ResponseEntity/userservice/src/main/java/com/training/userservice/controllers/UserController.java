package com.training.userservice.controllers;

import java.util.ArrayList;
import java.util.List;

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


@RestController
@RequestMapping("/user")
public class UserController {


	List<User> userList = new ArrayList<User>();
	
	public UserController(UserserviceApplication userserviceApplication) {
		userList.add(new User(1, "vivek", "Hyd", "vivek@gmail.com"));
		userList.add(new User(2, "anand", "bang", "anand@gmail.com"));
		userList.add(new User(3, "monika", "chennai", "monika@gmail.com"));
		userList.add(new User(4, "rachel", "pune", "rachel@gmail.com"));
	}
	
	
	
	@RequestMapping("/greet")
	public  ResponseEntity<String>  greet() {
		return ResponseEntity.status(HttpStatus.OK)
				.header("msg", "Hello")
				.body("Greetings to you");
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser(){
		HttpHeaders headers  = new HttpHeaders();
		headers.add("user-count", userList.size()+"");
		return new ResponseEntity<List<User>>(userList, headers, HttpStatus.OK);
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<User> getUserById(@PathVariable Integer uid) {
		User user =  userList.stream().filter(u->u.getUid()==uid)
				.findAny().orElseThrow(()-> new UserNotFoundException("User Not Found"));
		return ResponseEntity.status(HttpStatus.OK).header("user-count", userList.size()+"").body(user);
		
		
	}
	
	@GetMapping("/reqparam")
	public ResponseEntity<User> getUserwithId(@RequestParam(defaultValue = "2") Integer uid) {
		User user = userList.stream().filter(u->u.getUid()==uid)
				.findAny().orElseThrow(()-> new UserNotFoundException("User Not Found"));
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User u) {
		userList.add(u);
		User user =  userList.stream().filter(x->x.getUid()==u.getUid())
				.findAny().orElseThrow(()-> new UserNotFoundException("Unable to save User"));
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update/{uid}")
	public ResponseEntity<User> updateUser(@PathVariable int uid,@RequestBody User user) {
		User existing = userList.stream().filter(u->u.getUid()==uid)
		.findAny().orElseThrow(()-> new UserNotFoundException("User Not Found"));
		
		existing.setUname(user.getUname());
		existing.setAddress(user.getAddress());
		existing.setContact(user.getContact());
		
		return new ResponseEntity<User>(existing, HttpStatus.CREATED);
		
	}
	
	@PatchMapping("/updateprop/{uid}")
	public ResponseEntity<User> updateUserProp(@PathVariable int uid,@RequestBody User user) {
		User existing = userList.stream().filter(u->u.getUid()==uid)
		.findAny().orElseThrow(()-> new UserNotFoundException("User Not Found"));
		
		existing.setUname(user.getUname()!=null?user.getUname():existing.getUname());
		existing.setAddress(user.getAddress()!=null?user.getAddress():existing.getAddress());
		existing.setContact(user.getContact()!=null?user.getContact():existing.getContact());
		
		return new ResponseEntity<User>(existing, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{uid}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer uid) {
		User existing = userList.stream().filter(u->u.getUid()==uid)
				.findAny().orElseThrow(()-> new UserNotFoundException("User Not Found"));
		
		userList.remove(existing);
		
		return new ResponseEntity<String>("User Delted with uid "+uid, HttpStatus.ACCEPTED);
		
	}
	
	
	
}
