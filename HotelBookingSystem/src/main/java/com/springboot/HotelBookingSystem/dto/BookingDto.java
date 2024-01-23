package com.springboot.HotelBookingSystem.dto;

import java.time.LocalDate;

public class BookingDto {
	private LocalDate check_in;
	private LocalDate check_out;
	private int noOfAdults;
	private int noOfChildren;
	public LocalDate getCheck_in() {
		return check_in;
	}
	public void setCheck_in(LocalDate check_in) {
		this.check_in = check_in;
	}
	public LocalDate getCheck_out() {
		return check_out;
	}
	public void setCheck_out(LocalDate check_out) {
		this.check_out = check_out;
	}
	public int getNoOfAdults() {
		return noOfAdults;
	}
	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}
	public int getNoOfChildren() {
		return noOfChildren;
	}
	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}
	
}
