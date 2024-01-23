package com.springboot.HotelBookingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.model.Hr;
import com.springboot.HotelBookingSystem.repository.HrRepository;



@Service
public class HrService {
	
	@Autowired
	private HrRepository hrRepository;

	public Hr insertHr(Hr hr) {
		// TODO Auto-generated method stub
		return hrRepository.save(hr);
	}

	public Hr getHrById(int hrid) {
		// TODO Auto-generated method stub
		return null;
	}


}

