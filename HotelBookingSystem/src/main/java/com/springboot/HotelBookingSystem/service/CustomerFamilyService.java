package com.springboot.HotelBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.CustomerFamily;
import com.springboot.HotelBookingSystem.repository.CustomerFamilyRepository;

@Service
public class CustomerFamilyService {
	@Autowired
	private CustomerFamilyRepository customerFamilyRepository;
	
	public CustomerFamily insert(CustomerFamily family) {
		// TODO Auto-generated method stub
		return customerFamilyRepository.save(family);
	}

	public List<CustomerFamily> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return customerFamilyRepository.findAll(pageable).getContent();
	}

	public CustomerFamily getById(int fid) throws InvalidIdException {
		Optional<CustomerFamily> optional = customerFamilyRepository.findById(fid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Family ID invalid");
		}
		return optional.get();
	}

}
