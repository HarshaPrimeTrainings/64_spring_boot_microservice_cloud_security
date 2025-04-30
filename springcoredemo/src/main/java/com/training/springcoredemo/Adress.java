package com.training.springcoredemo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
public class Adress {
	
	
	private String state;
	private String city;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Adress [state=" + state + ", city=" + city + "]";
	}
	
	

}
