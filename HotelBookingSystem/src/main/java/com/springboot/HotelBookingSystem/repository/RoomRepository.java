package com.springboot.HotelBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.HotelBookingSystem.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	List<Room> findByHotelId(int hid);

	
	@Query("SELECT r FROM Room r WHERE r.id IN :roomIds")
	List<Room> getAllByIds(@Param("roomIds") List<Integer> roomIds);

	
	

	

}
