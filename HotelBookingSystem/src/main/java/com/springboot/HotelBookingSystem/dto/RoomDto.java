package com.springboot.HotelBookingSystem.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.springboot.HotelBookingSystem.enumerate.RoomType;

public class RoomDto {
	private int id;
	@Enumerated(EnumType.STRING)
	private RoomType room_type;
	private double price;
	
	
	
	public RoomType getRoom_type() {
		return room_type;
	}
	public void setRoom_type(RoomType room_type) {
		this.room_type = room_type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
