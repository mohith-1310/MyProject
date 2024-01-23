package com.springboot.HotelBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.HotelAdmin;
import com.springboot.HotelBookingSystem.repository.HotelAdminRepository;

@Service
public class HotelAdminService {
	
	@Autowired
	private HotelAdminRepository hotelAdminRepository;

	public HotelAdmin postAdmin(HotelAdmin hotelAdmin) {
		// TODO Auto-generated method stub
		return hotelAdminRepository.save(hotelAdmin);
	}

	public HotelAdmin getAdminById(int aid) throws InvalidIdException {
		Optional<HotelAdmin> optional = hotelAdminRepository.findById(aid);
		if(!optional.isPresent())
			throw new InvalidIdException("HotelAdmin id invalid");
		return optional.get();
	}

	public HotelAdmin getOne(int id) throws InvalidIdException {
		Optional<HotelAdmin> optional = hotelAdminRepository.findById(id);
		if(!optional.isPresent())
			throw new InvalidIdException("HotelAdmin id invalid");
		return optional.get();
	}

	public void deleteHotelAdmin(HotelAdmin hotelAdmin) {
		hotelAdminRepository.delete(hotelAdmin);
		
	}

	public List<HotelAdmin> getByExecutive(int eid) {
		// TODO Auto-generated method stub
		return hotelAdminRepository.findByExecutiveId(eid);
	}

	public HotelAdmin insertRoom(HotelAdmin oldHotelAdmin) {
		// TODO Auto-generated method stub
		return hotelAdminRepository.save(oldHotelAdmin);
	}

	public HotelAdmin getByUserId(int uid) {
		// TODO Auto-generated method stub
		return hotelAdminRepository.findByUserId(uid);
	}

	public List<HotelAdmin> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return hotelAdminRepository.findAll(pageable).getContent();
	}

}
