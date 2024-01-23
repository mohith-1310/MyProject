package com.springboot.HotelBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.model.Hr;

public interface HrRepository extends JpaRepository<Hr, Integer>{
	
	
	

}
