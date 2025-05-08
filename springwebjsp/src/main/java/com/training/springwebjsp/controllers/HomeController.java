package com.training.springwebjsp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.training.springwebjsp.model.User;

@Controller
public class HomeController {

	List<User> users = new ArrayList<>();
	
	public HomeController() {
		users.add(new User(1, "vivek", "Hyd", "vivek@gmai.com"));
		users.add(new User(2, "anand", "bang", "anand@gmai.com"));
		users.add(new User(3, "monika", "chennai", "monika@gmai.com"));
		users.add(new User(4, "raju", "pune", "raju@gmai.com"));
		
	}
	
	
	@RequestMapping(value = {"/","/users"})
	public ModelAndView usersList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("userlist",users );
		mav.setViewName("users");
		return mav;
	}
	
	
	@RequestMapping(value = "/home")
	public ModelAndView sendUser() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "Data Coming from Controller");
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping("/adduser")
	public String addUser() {
		return "adduser";
	}
	
	@RequestMapping(value = "/save" ,method = RequestMethod.POST)
	public ModelAndView saveuser(User u) {
		users.add(u);
		ModelAndView mav = new ModelAndView();
		mav.addObject("userlist",users);
		mav.setViewName("users");
		return mav;
	}
	
}
