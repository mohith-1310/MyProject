package com.springboot.HotelBookingSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	public Hotel postHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public List<Hotel> getAllHotels(Pageable pageable) {
		return hotelRepository.findAll(pageable).getContent();
	}

	public List<Hotel> getAllHotelsByEid(int eid) {
		return hotelRepository.findByExecutiveId(eid);
	}

	public List<Hotel> getAllHotelsByLocation(int lid) {
		return hotelRepository.findByLocationId(lid);
	}

	public Hotel getHotelsByHid(int hid) throws InvalidIdException {
		Optional<Hotel> optional = hotelRepository.findById(hid);
		if (!optional.isPresent()) {
			throw new InvalidIdException("location id invalid");
		}
		return optional.get();
	}

	public Hotel getOne(int id) throws InvalidIdException {
		Optional<Hotel> optional = hotelRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("hotel id invalid");
		}
		return optional.get();
	}

	public void deleteHotel(Hotel hotel) {
		hotelRepository.delete(hotel);
	}

	public List<Hotel> getByAdmin(int aid) {
		// TODO Auto-generated method stub
		return hotelRepository.findByHotelAdminId(aid);
	}

	public Hotel getByHotelId(int hid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional<Hotel> optional = hotelRepository.findById(hid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Hotel ID invalid");
		}
		return optional.get();
	}

	public Hotel getById(int hid) throws InvalidIdException {
		Optional<Hotel> optional = hotelRepository.findById(hid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Hotel ID Invalid");
		}
		return optional.get();
	}

	public Hotel findByHotelId(int hid) throws InvalidIdException {
		 Optional<Hotel> optional = hotelRepository.findById(hid);
		 if(!optional.isPresent()) {
			 throw new InvalidIdException("Hotle ID invalid....");
		 }
		return optional.get();
	}

	public List<Hotel> getHotelsByLocationCinCout(String lname, LocalDate cin, LocalDate cout) {
		// TODO Auto-generated method stub
		return hotelRepository.getHotelsByCinCoutLocation(lname,cin,cout);
	}

	public List<Hotel> getHotelsByLocation(String lname) {
		// TODO Auto-generated method stub
		return hotelRepository.getHotelsByLocation(lname);
	}

	
}
