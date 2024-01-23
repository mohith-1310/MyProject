package com.springboot.HotelBookingSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.CustomerRoom;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.repository.CustomerRoomRepository;

@Service
public class CustomerRoomService {

	@Autowired
	private CustomerRoomRepository customerRoomRepository;
	
	public CustomerRoom insert(CustomerRoom booking) {
		// TODO Auto-generated method stub
		return customerRoomRepository.save(booking);
	}
	
	
	public CustomerRoom getById(int bid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional<CustomerRoom> optional = customerRoomRepository.findById(bid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Booking ID invalid....");
		}
		return optional.get();
	}
	public void deleteBooking(CustomerRoom booking) {
			customerRoomRepository.delete(booking);
		
	}


	public List<CustomerRoom> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return customerRoomRepository.findAll(pageable).getContent();
	}


	public List<CustomerRoom> getByCustomer(int cid) {
		// TODO Auto-generated method stub
		return customerRoomRepository.findByCustomerId(cid);
	}


	  public double calculateTotalPrice(int customerId) throws InvalidIdException{
	        List<CustomerRoom> bookedRooms = customerRoomRepository.findByCustomerId(customerId);

	        // Calculate total price for the booked rooms
	        double totalPrice = 0.0;
	        for (CustomerRoom booking : bookedRooms) {
	            totalPrice += booking.getRoom().getPrice();
	        }

	        return totalPrice;
	    }


	public List<CustomerRoom> getByDateandHotel(int hid, LocalDate date) {
		// TODO Auto-generated method stub
		return customerRoomRepository.findCustomerByHidAndDate(hid,date);
	}


	public List<Customer> getCustomers(int hid) {
		// TODO Auto-generated method stub
		return customerRoomRepository.getCustomersVisited(hid);
	}


	public List<CustomerRoom> getByHotel(int hid) {
		// TODO Auto-generated method stub
		return customerRoomRepository.getByHotel(hid);
	}


	


}




	



	

	


