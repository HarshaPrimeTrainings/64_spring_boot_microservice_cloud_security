package com.training.ottcataloge;

import org.springframework.stereotype.Component;

@Component
public class Movie {
	
	private String name;
	private String cataogery;
	private String rating;
	
	public Movie() {
		
	}
	
	
	public Movie(String name, String cataogery, String rating) {
		super();
		this.name = name;
		this.cataogery = cataogery;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCataogery() {
		return cataogery;
	}
	public void setCataogery(String cataogery) {
		this.cataogery = cataogery;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Movie [name=" + name + ", cataogery=" + cataogery + ", rating=" + rating + "]";
	}
	
	
	

}
