package com.training.springwebdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
	
	@RequestMapping("/hello")
	public String greet() {
		return "demo";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "greet";
	}

}
