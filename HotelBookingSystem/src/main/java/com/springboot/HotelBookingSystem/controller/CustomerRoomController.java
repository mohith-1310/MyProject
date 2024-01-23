package com.springboot.HotelBookingSystem.controller;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.springboot.HotelBookingSystem.dto.BookingDto;
import com.springboot.HotelBookingSystem.enumerate.BookingStatus;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.CustomerRoom;
import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.service.CustomerRoomService;
import com.springboot.HotelBookingSystem.service.CustomerService;
import com.springboot.HotelBookingSystem.service.HotelService;
import com.springboot.HotelBookingSystem.service.RoomService;



@RestController
@RequestMapping("/feelhome")
@CrossOrigin(origins = {"http://localhost:3000"})
public class CustomerRoomController {

	@Autowired
	private CustomerRoomService customerRoomService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RoomService roomService;


	@GetMapping("booking/getone/{bid}")
	public ResponseEntity<?> getOne(@PathVariable("bid") int bid) {
		try {
			CustomerRoom booking = customerRoomService.getById(bid);
			return ResponseEntity.ok().body(booking);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("booking/cancel/{bid}")
	public ResponseEntity<?> deleteBooking(@PathVariable("bid") int bid) {

		try {
			/* fetch booking id */
			CustomerRoom booking = customerRoomService.getById(bid);
			/* cancel booking after fetching ID */
			customerRoomService.deleteBooking(booking);
			/* return message if cancellation is successful */
			return ResponseEntity.ok().body("Booking cancelled successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/booking/getall")
	public List<CustomerRoom> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return customerRoomService.getAll(pageable);

	}

	@PutMapping("/booking/update/{bid}")
	public ResponseEntity<?> updateBooking(@PathVariable("bid") int bid, @RequestBody BookingDto newBooking) {
		try {
			CustomerRoom oldBooking = customerRoomService.getById(bid);
			if (newBooking.getCheck_in() != null)
				oldBooking.setCheck_in(newBooking.getCheck_in());
			if (newBooking.getCheck_out() != null)
				oldBooking.setCheck_out(newBooking.getCheck_out());
			if (newBooking.getNoOfAdults() != 0)
				oldBooking.setNoOfAdults(newBooking.getNoOfAdults());
			if (newBooking.getNoOfChildren() != 0)
				oldBooking.setNoOfChildren(newBooking.getNoOfChildren());

			oldBooking = customerRoomService.insert(oldBooking);
			return ResponseEntity.ok().body(oldBooking);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/booking/getall/{cid}")
	public ResponseEntity<?> getByCustomer(@PathVariable("cid") int cid) {
//	fetch customer details by id
		try {
			Customer customer = customerService.getById(cid);
			List<CustomerRoom> list = customerRoomService.getByCustomer(cid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PostMapping("/book/{cid}/{rid}/{hid}")
	public ResponseEntity<?> insert(@PathVariable("cid") int cid,
	                                @PathVariable("rid") int rid,
	                                @PathVariable("hid") int hid,
	                                @RequestBody CustomerRoom customerRoom) {
	    try {
	        Customer customer = customerService.getById(cid);
	        Room room = roomService.getById(rid);
	        Hotel hotel = hotelService.getByHotelId(hid);
	        customerRoom.setCustomer(customer);
	        customerRoom.setRoom(room);
	        customerRoom.setHotel(hotel);

	        // Calculate the number of days
	        long noOfDays = ChronoUnit.DAYS.between(customerRoom.getCheck_in(), customerRoom.getCheck_out());

	        // Set the number of days in customerRoom
	        customerRoom.setNoOfDays(noOfDays);

	        // Calculate total price based on your pricing logic
	        double totalPrice = calculateTotalPrice(customerRoom);

	        // Set the total price in customerRoom
	        customerRoom.setTotalPrice(totalPrice);
	        customerRoom.setStatus(BookingStatus.BOOKED.toString());

	        customerRoom = customerRoomService.insert(customerRoom);
	        
	        return ResponseEntity.ok().body(customerRoom);
	    } catch (InvalidIdException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

	// Example method to calculate total price (adjust based on your pricing logic)
	private double calculateTotalPrice(CustomerRoom customerRoom) {
	    // Example: Assuming a fixed price per day
	    double pricePerDay = customerRoom.getRoom().getPrice(); // Adjust this based on your pricing

	    // Calculate total price
	    return pricePerDay * customerRoom.getNoOfDays();
	}




	
	
	//list of customers who have booked rooms in a given hotel. take hotel id as path variable.
	@GetMapping("/customers/{hid}")
	public ResponseEntity<?> getCustomersVisitedHotel(@PathVariable("hid") int hid) {
		try {
			Hotel hotel = hotelService.findByHotelId(hid);
			List<Customer> list = customerRoomService.getCustomers(hid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("hotel/booking/getall/{hid}")
	public ResponseEntity<?> getByHotel(@PathVariable("hid") int hid) {
//	fetch customer details by id
		try {
			Hotel hotel = hotelService.getById(hid);
			List<CustomerRoom> list = customerRoomService.getByHotel(hid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	}


