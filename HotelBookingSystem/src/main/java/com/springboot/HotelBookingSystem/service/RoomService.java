package com.springboot.HotelBookingSystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.repository.CustomerRoomRepository;
import com.springboot.HotelBookingSystem.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private CustomerRoomRepository bookingRepository;
	
	public Room getById(int rid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional<Room> optional = roomRepository.findById(rid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Room ID invalid");
		}
		return optional.get();
	}

	public Room insert(Room room) {
		// TODO Auto-generated method stub
		return roomRepository.save(room);
	}

	public List<Room> getRoomsByHid(int hid) {
		// TODO Auto-generated method stub
		return roomRepository.findByHotelId(hid);
	}

	public Room insertRoom(Room oldRoom) {
		// TODO Auto-generated method stub
		return roomRepository.save(oldRoom);
	}

	public void deleteRoom(Room room) {
		// TODO Auto-generated method stub
		roomRepository.delete(room);
	}

	public List<Room> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return roomRepository.findAll(pageable).getContent();
	}

	public List<Room> getAllAvailableRoomsByHotelId(int hotelId, LocalDate checkIn, LocalDate checkOut) { 
		 List<Room> rooms = roomRepository.findByHotelId(hotelId);
		 List<Room> availableRooms = new ArrayList<>();
		 for(Room room : rooms) {
			 int noOfBookings = bookingRepository.findNumberOfBookings(room.getId(),checkIn, checkOut);
			 if(room.getTotalRooms() > noOfBookings) {
				 availableRooms.add(room);
			 }
			 else {
				 System.out.println("No rooms available of type :::" + room.getRoom_type());
			 }
			 
		 }
		return availableRooms;
	}

	public List<Room> getRoomsByIds(List<Integer> roomIds) throws InvalidIdException {
	    List<Room> rooms = roomRepository.findAllById(roomIds);
	    if (rooms.isEmpty()) {
	        throw new InvalidIdException("No valid room IDs provided");
	    }
	    return rooms;
	}

	public List<Room> getAllByIds(List<Integer> roomIds) {
		// TODO Auto-generated method stub
		return roomRepository.getAllByIds(roomIds);
	}

}
