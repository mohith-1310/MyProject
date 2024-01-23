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

import com.springboot.HotelBookingSystem.dto.RoomDto;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.service.HotelService;
import com.springboot.HotelBookingSystem.service.RoomService;

@RestController
@RequestMapping("/feelhome")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RoomController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private HotelService hotelService;

	@PostMapping("/room/add/{hid}")
	public ResponseEntity<?> insert(@PathVariable("hid") int hid, @RequestBody Room room) {

		try {
			Hotel hotel = hotelService.getOne(hid);
			room.setHotel(hotel);
			room = roomService.insert(room);
			return ResponseEntity.ok().body(room);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/deleteroom/{id}")
	public ResponseEntity<?> deleteExecutive(@PathVariable("id") int id) {
		try {
			Room room = roomService.getById(id);
            roomService.deleteRoom(room);
			
			return ResponseEntity.ok().body("room deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping("/getallrooms")
	public List<Room> getAllRooms(@RequestParam(value="page",required=false,defaultValue="0")Integer page,
	                        @RequestParam(value="size",required=false,defaultValue="100000")Integer size ){
		Pageable pageable = PageRequest.of(page,size);
		return roomService.getAll(pageable);
	}
	
	@GetMapping("/get/{rid}")
	public ResponseEntity<?> getById(@PathVariable("rid") int rid) {
		try{
			Room room = roomService.getById(rid);
		return ResponseEntity.ok().body(room);
		}catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	@GetMapping("/get/availability/{hotelId}/{checkIn}/{checkOut}")
	public List<Room> getAllAvailableRoomsByHotelId(@PathVariable("hotelId") int hotelId,
			@PathVariable("checkIn")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn, 
			@PathVariable("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {

		return roomService.getAllAvailableRoomsByHotelId(hotelId, checkIn, checkOut);
	}
	 
	
	

	@GetMapping("/rooms/getByHotel/{hid}")
	public List<Room> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size,
			@PathVariable("hid") int hid) {
		/*
		 * Pageable pageable = PageRequest.of(page, size); return
		 * roomService.getAll(pageable);
		 */
		return roomService.getRoomsByHid(hid);
	}

	@PutMapping("/room/update/{rid}")
	public ResponseEntity<?> updateBooking(@PathVariable("rid") int rid, @RequestBody RoomDto newRoom) {
		try {
			Room oldRoom = roomService.getById(rid);

			if (newRoom.getId() != 0)
				oldRoom.setId(newRoom.getId());
			if (newRoom.getRoom_type() != null)
				oldRoom.setRoom_type((newRoom.getRoom_type()));
			if (newRoom.getPrice() != 0)
				oldRoom.setPrice(newRoom.getPrice());

			oldRoom = roomService.insert(oldRoom);
			return ResponseEntity.ok().body(oldRoom);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
}
