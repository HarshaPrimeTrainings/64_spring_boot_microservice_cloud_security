package com.training.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Demoservice {

	@Qualifier("myimpl")
	@Autowired
	private DemoInterface demointerface;

	
	public void execute() {
		System.out.println(demointerface.greet());
	}
	
}
