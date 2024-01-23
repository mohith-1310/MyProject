package com.springboot.HotelBookingSystem.dto;

public class HotelDto {
	private String name;
	private String address;
	private String email;
	private String phone_number;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	@Override
	public String toString() {
		return "HotelDto [name=" + name + ", address=" + address + ", email=" + email + ", phone_number=" + phone_number
				+ ", getName()=" + getName() + ", getAddress()=" + getAddress() + ", getEmail()=" + getEmail()
				+ ", getPhone_number()=" + getPhone_number() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
