package com.springboot.HotelBookingSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.repository.CustomerRepository;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer insert(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	public Customer getById  (int cid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional <Customer> optional = customerRepository.findById(cid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Customer ID invalid...");
		}
		return optional.get();
		
	}

	public List<Customer> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return customerRepository.findAll(pageable).getContent();
	}

	public List<Customer> getByDateandHotel(int hid, LocalDate date) {
		// TODO Auto-generated method stub
		return customerRepository.findCustomerByHidAndDate(hid,date);
	}

	
	public Customer getByUserId(int uid) {
		// TODO Auto-generated method stub
		return customerRepository.findByUserId(uid);
	}
}
