package com.springboot.HotelBookingSystem.model;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.springboot.HotelBookingSystem.enumerate.RoomType;


@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Enumerated(EnumType.STRING)
	private RoomType room_type;
	private double price;
	private int totalRooms;
	
	

	@ManyToOne
	private Hotel hotel;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	public int getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(int totalRooms) {
		this.totalRooms = totalRooms;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", room_type=" + room_type + ", price=" + price + ", totalRooms=" + totalRooms
				+ ", hotel=" + hotel + "]";
	}

	
	
	
	
}
