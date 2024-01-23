package com.springboot.HotelBookingSystem.dto;


import javax.persistence.OneToOne;

import com.springboot.HotelBookingSystem.model.Executive;

public class HotelAdminDto {
	private String name;
	private String email;
	
	@OneToOne
	private Executive executive;
	
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
	public Executive getExecutive() {
		return executive;
	}
	public void setExecutive(Executive executive) {
		this.executive = executive;
	}
	
	
	
}
