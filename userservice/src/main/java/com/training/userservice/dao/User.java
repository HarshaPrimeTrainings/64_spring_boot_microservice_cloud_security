package com.training.userservice.dao;


public class User {
	
	private Integer uid;
	private String uname;
	private String address;
	private String contact;
	
	public User(Integer uid, String uname, String address, String contact) {
		this.uid = uid;
		this.uname = uname;
		this.address = address;
		this.contact = contact;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	 
	
	

}
