package com.training.userservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.client.RestTemplate;

import com.training.userservice.UserserviceApplication;
import com.training.userservice.dao.User;
import com.training.userservice.dto.OrderDto;
import com.training.userservice.dto.UserDto;
import com.training.userservice.exception.UserNotFoundException;
import com.training.userservice.services.UserManageService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	UserManageService userService;
	
	@Value("${orderservice.url}")
	private String orderurl;
	
	int cout = 0;
	@Retry(name = "greetfallback",fallbackMethod = "fallbackgreetFromorder")
	@GetMapping("/hello")
	public ResponseEntity<String> greetFromorder() {
		cout = cout+1;
		System.out.println("RETRIED -------------->  "+cout);
		ResponseEntity<String> resp = restTemplate.getForEntity(orderurl+"/ordrgreet", String.class);
		return resp;
	}
	
	public ResponseEntity<String> fallbackgreetFromorder(Throwable ex){
		
		return new ResponseEntity<String>("ORDER SERVICE NOT RESPONDING RETRIED: "+cout,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@CircuitBreaker(name = "orderfallback",fallbackMethod = "getOrderFallback")
	 @GetMapping("/order/{uid}")		
	 public UserDto getOrders(@PathVariable Integer uid) {
		 User user =  userService.getUserById(uid);
		 List<OrderDto> ordders = restTemplate.getForObject(orderurl+"/order/"+uid, List.class);
		 UserDto dto = new UserDto();
		 dto.setUid(user.getUid());
		 dto.setUname(user.getUname());
		 dto.setAddress(user.getAddress());
		 dto.setContact(user.getContact());
		 dto.setOrders(ordders);
		 return dto;
	 }
	 
	 public UserDto getOrderFallback(Integer uid,Throwable ex) {
		 UserDto u = new UserDto();
		  u.setUname("falbck");
		  OrderDto order = new OrderDto();
		  order.setCatagery("fallbck order");
		  List<OrderDto> ol= new ArrayList<>();
		  ol.add(order);
		  u.setOrders(ol);
		  return u;
	 }
	 

	 @PostMapping("/order")
	 public OrderDto saveOrder(@RequestBody OrderDto order) {
		 return restTemplate.postForObject("http://localhost:8089/save", order, OrderDto.class);
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
		headers.add("user-count", userService.getAllUser().size()+"");
		return new ResponseEntity<List<User>>(userService.getAllUser(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<User> getUserById(@PathVariable Integer uid) {
		User user =  userService.getUserById(uid);
		return ResponseEntity.status(HttpStatus.OK).header("user-count", userService.getAllUser()+"").body(user);
	}
	
	@GetMapping("/name/{uname}")
	public ResponseEntity<List<User>> getUsersByName(@PathVariable String uname){
		return new ResponseEntity<List<User>>(userService.getUserByName(uname),HttpStatus.OK);
	}
	
	@GetMapping("/addr/{addr}")
	public ResponseEntity<List<User>> getUsersByAddr(@PathVariable String addr){
		return new ResponseEntity<List<User>>(userService.getUserByaddr(addr),HttpStatus.OK);
	}
	
	@GetMapping("/{pageSize}/{pageNumber}")
	public ResponseEntity<List<User>> getallUsersPage(@PathVariable int pageNumber,@PathVariable int pageSize){
		return new ResponseEntity<List<User>>(userService.findAllusersPage(pageNumber,pageSize),HttpStatus.OK);
	}
	
	@GetMapping("/sort/{column}/{sortOrder}")
	public ResponseEntity<List<User>> getallUsersSorted(@PathVariable String column,@PathVariable(required = false) String sortOrder){
		return new ResponseEntity<List<User>>(userService.findSortedusers(column,sortOrder),HttpStatus.OK);
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
