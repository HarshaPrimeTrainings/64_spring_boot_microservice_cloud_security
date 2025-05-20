package com.training.userservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.training.userservice.dao.User;
import com.training.userservice.exception.UserNotFoundException;

@Service
public class UserManageService {

	private List<User> userList = new ArrayList<>();
	
	public UserManageService() {
		userList.add(new User(1, "vivek", "Hyd", "vivek@gmail.com"));
		userList.add(new User(2, "anand", "bang", "anand@gmail.com"));
		userList.add(new User(3, "monika", "chennai", "monika@gmail.com"));
		userList.add(new User(4, "rachel", "pune", "rachel@gmail.com"));
	}
	
	
	public List<User> getAllUser(){
		return userList;
	}
	
	public Optional<User> getUserByIdOptional(Integer uid){
		return userList.stream().filter(u->u.getUid()==uid).findFirst();
	}
	
	public User getUserById(Integer uid) {
		return getUserByIdOptional(uid).orElseThrow(()-> new UserNotFoundException("User Not Found"));
	}
	
	public User saveUser(User u) {
		userList.add(u);
		return getUserByIdOptional(u.getUid()).orElseThrow(()-> new UserNotFoundException("Unable to Save User"));
	}
	
	public User updateUser(Integer uid,User user) {
		User existing = getUserById(uid);
		existing.setUname(user.getUname());
		existing.setAddress(user.getAddress());
		existing.setContact(user.getContact());
		return existing;
	}
	
	public User updateUserPartially(Integer uid, User user) {
		User existing = getUserById(uid);
		existing.setUname(user.getUname() != null ? user.getUname() : existing.getUname());
		existing.setAddress(user.getAddress() != null ? user.getAddress() : existing.getAddress());
		existing.setContact(user.getContact() != null ? user.getContact() : existing.getContact());
		return existing;
	}
	
	public String deleteUser(Integer uid) {
		User existing = getUserById(uid);
		userList.remove(existing);
		return "User Delted with uid "+uid;
	}
	
	
}
