package com.training.userservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.userservice.dao.User;
import com.training.userservice.dao.UserRepository;
import com.training.userservice.exception.UserNotFoundException;

@Service
public class UserManageService {

	@Autowired
	UserRepository userRepo;
	
	public List<User> getAllUser(){
		return (List<User>) userRepo.findAll();
	}
	
	public User getUserById(Integer uid) {
		return userRepo.findById(uid).orElseThrow(()->new UserNotFoundException("User Not found with "+ uid));
	}
	
	public User saveUser(User u) {
		return userRepo.save(u);
	}
	
	public User updateUser(Integer uid,User user) {
		User existing = getUserById(uid);
		existing.setUname(user.getUname());
		existing.setAddress(user.getAddress());
		existing.setContact(user.getContact());
		return userRepo.save(existing);
	}
	
	public User updateUserPartially(Integer uid, User user) {
		User existing = getUserById(uid);
		existing.setUname(user.getUname() != null ? user.getUname() : existing.getUname());
		existing.setAddress(user.getAddress() != null ? user.getAddress() : existing.getAddress());
		existing.setContact(user.getContact() != null ? user.getContact() : existing.getContact());
		return userRepo.save(existing);
	}
	
	public String deleteUser(Integer uid) {
		userRepo.deleteById(uid);
		return "User Delted with uid "+uid;
	}
	
	public List<User> getUserByName(String uname){
		return userRepo.getByUname(uname);
	}
	
	public List<User> getUserByaddr(String addr){
		return userRepo.searchUseraddr(addr);
	}
	
}
