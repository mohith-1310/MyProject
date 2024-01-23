package com.springboot.HotelBookingSystem.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate date;
	private String payment_mode;
	private String status;
	private double total_fare;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotal_fare() {
		return total_fare;
	}
	public void setTotal_fare(double total_fare) {
		this.total_fare = total_fare;
	}
	
	
	
}
