package com.springboot.HotelBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.repository.ExecutiveRepository;




@Service
public class ExecutiveService {
	
	
	@Autowired
	private ExecutiveRepository executiveRepository;

	public Executive insertExecutive(Executive executive) {
		return executiveRepository.save(executive);
	}

	public List<Executive> getAll(Pageable pageable) {
		return executiveRepository.findAll(pageable).getContent();
	}

	

	public Executive getById(int eid) throws InvalidIdException {
		Optional<Executive> optional = executiveRepository.findById(eid);
		if(!optional.isPresent())
			throw new InvalidIdException("executive id invalid");
		return optional.get();
	}


	public void deleteExecutive(Executive executive) {
		executiveRepository.delete(executive);
	}

	public Executive getByExecutiveId(int eid) throws InvalidIdException {
		Optional<Executive> executive = executiveRepository.findById(eid);
		if(!executive.isPresent()) {
			throw new InvalidIdException("");
		}
		return executive.get();
	}

	public Executive getByUserId(int uid) {
		// TODO Auto-generated method stub
		return executiveRepository.findByUserId(uid);
	}

	
	
	}

	


