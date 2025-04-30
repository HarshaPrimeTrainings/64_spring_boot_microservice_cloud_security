package com.learning.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class ExternalClass {
	
	private String greet;

	public String getGreet() {
		return greet;
	}

	public void setGreet(String greet) {
		this.greet = greet;
	}
	
	
	
	
}
