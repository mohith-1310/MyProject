package com.springboot.HotelBookingSystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.dto.HotelDto;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.HotelAdmin;
import com.springboot.HotelBookingSystem.model.Location;
import com.springboot.HotelBookingSystem.service.ExecutiveService;
import com.springboot.HotelBookingSystem.service.HotelAdminService;
import com.springboot.HotelBookingSystem.service.HotelService;
import com.springboot.HotelBookingSystem.service.LocationService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/feelhome")
public class HotelController {

	@Autowired
	private ExecutiveService executiveService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private HotelAdminService hotelAdminService;

	@Autowired
	private LocationService locationService;

	@PostMapping("/hotel/add/{aid}/{lid}")
	public ResponseEntity<?> postHotel(@PathVariable("aid") int aid, @PathVariable("lid") int lid,
			@RequestBody Hotel hotel) {

		try {
			// Getting the Admin details from admin id
			HotelAdmin adminDetails = hotelAdminService.getAdminById(aid);

			// Getting the executive details from Admin
			Executive executive = adminDetails.getExecutive();

			// Getting the location details by location Id
			Location location = locationService.getLocationById(lid);

			// Setting adminDetails to hotel
			hotel.setHotelAdmin(adminDetails);

			// Setting executive details to hotel
			hotel.setExecutive(executive);

			// Setting location to hotel
			hotel.setLocation(location);

			hotel = hotelService.postHotel(hotel);
			return ResponseEntity.ok().body(hotel);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/getallhotels")
	public List<Hotel> getAllHotels(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return hotelService.getAllHotels(pageable);

	}

	@GetMapping("/getAllByExecutive/{eid}")
	public ResponseEntity<?> getAllHotelsByEid(@PathVariable("eid") int eid) {
		try {
			// fetch executive obj using executive id
			Executive executive = executiveService.getById(eid);

			// get all hotels using executive id
			List<Hotel> list = hotelService.getAllHotelsByEid(eid);

			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}

	@GetMapping("/hotel/getAll/Location/{lid}")
	public ResponseEntity<?> getAllHotelsByLid(@PathVariable("lid") int lid) {
		try {
			// fetch location obj using location id
			Location location = locationService.getOne(lid);

			// get all hotels using location id
			List<Hotel> list = hotelService.getAllHotelsByLocation(lid);

			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}

	@GetMapping("/getByHotel/{hid}")
	public ResponseEntity<?> getHotelsByHid(@PathVariable("hid") int hid) {
		try {
			// get all hotels using hotel id
			Hotel hotel = hotelService.getHotelsByHid(hid);

			return ResponseEntity.ok().body(hotel);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}

	@DeleteMapping("/hotel/delete/{id}")
	public ResponseEntity<?> deleteHotel(@PathVariable("id") int id) {

		try {

			// validate id
			Hotel hotel = hotelService.getOne(id);

			// delete
			hotelService.deleteHotel(hotel);
			return ResponseEntity.ok().body("hotel deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/updatehotel/{hid}") 
	public ResponseEntity<?> updateHotel(@PathVariable("hid") int hid, @RequestBody HotelDto newHotel) {
		try {

			// Getting the Hotel details by Id
			Hotel oldHotel = hotelService.getHotelsByHid(hid);

			// Checking the values in new object and setting to old object
			if (newHotel.getName() != null)
				oldHotel.setName(newHotel.getName());
			if (newHotel.getEmail() != null)
				oldHotel.setEmail(newHotel.getEmail());
			if (newHotel.getAddress() != null)
				oldHotel.setAddress(newHotel.getAddress());
			if (newHotel.getPhone_number() != null)
				oldHotel.setPhone(newHotel.getPhone_number());

			// Inserting the updated values to Hotel
			oldHotel = hotelService.postHotel(oldHotel);

			return ResponseEntity.ok().body(oldHotel);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/hotel/getbyadmin/{aid}")
	public ResponseEntity<?> getByAdmin(@PathVariable("aid") int aid) {
//		fetch admin details by id
		try {
			HotelAdmin hotelAdmin = hotelAdminService.getOne(aid);

			List<Hotel> list = hotelService.getByAdmin(aid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("gethotels/{lname}/{cin}/{cout}")
	public List<Hotel> getHotels(@PathVariable("lname") String lname,
						   @PathVariable("cin")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate cin,
						   @PathVariable("cout")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate cout) {
		List<Hotel> list = hotelService.getHotelsByLocationCinCout(lname,cin,cout);
		return list;
	}
	
	
	@GetMapping("gethotels/{lname}")
	public List<Hotel> getHotels(@PathVariable("lname") String lname)
						    {
		List<Hotel> list = hotelService.getHotelsByLocation(lname);
		return list;
	}

	
}
