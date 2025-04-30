package com.training.springcoredemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Qualifier("demoimpl")
@Component("demoimpl")
public class DemoInterfaceImpl implements DemoInterface{

	@Override
	public String greet() {
		return "Hello From DemoInterfaceImpl";
	}

}
