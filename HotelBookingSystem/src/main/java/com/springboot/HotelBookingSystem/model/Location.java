package com.springboot.HotelBookingSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String pincode;
	
	@OneToOne
	private Executive executive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Executive getExecutive() {
		return executive;
	}

	public void setExecutive(Executive executive) {
		this.executive = executive;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", pincode=" + pincode + ", executive=" + executive + "]";
	}
	
}
