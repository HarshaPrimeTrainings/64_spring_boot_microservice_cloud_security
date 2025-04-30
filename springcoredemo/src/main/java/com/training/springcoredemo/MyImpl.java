package com.training.springcoredemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Qualifier("myimpl")
@Component("myimpl")
public class MyImpl implements DemoInterface{

	@Override
	public String greet() {
		return "Hello from MyImpl";
	}

}
