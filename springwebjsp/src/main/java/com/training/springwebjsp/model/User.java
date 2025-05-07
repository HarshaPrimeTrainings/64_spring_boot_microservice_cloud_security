package com.training.springwebjsp.model;

public class User {

	private int uid;
	private String name;
	private String adress;
	private String email;
	
	public User(int uid, String name, String adress, String email) {
		super();
		this.uid = uid;
		this.name = name;
		this.adress = adress;
		this.email = email;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
