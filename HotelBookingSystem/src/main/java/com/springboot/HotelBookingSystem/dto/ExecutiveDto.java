package com.springboot.HotelBookingSystem.dto;

public class ExecutiveDto {
	
	private String name;
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "ExecutiveDto [name=" + name + ", email=" + email + "]";
	}
	
	

}
