package com.springboot.HotelBookingSystem.exception;

public class InvalidIdException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public InvalidIdException(String message) {
		super();
		this.message = message;
	}
}
