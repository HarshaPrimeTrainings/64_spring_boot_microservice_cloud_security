package com.training.userservice.dto;

public class OrderDto {

	private Integer oid;
	private String status;
	private String catagery;
	private Integer uid;
	
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCatagery() {
		return catagery;
	}
	public void setCatagery(String catagery) {
		this.catagery = catagery;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	
}
