package com.springboot.HotelBookingSystem.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.CustomerRoom;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.Room;

public interface CustomerRoomRepository extends JpaRepository<CustomerRoom, Integer>{

	List<CustomerRoom> findByCustomerId(int cid);

	@Query("select COUNT(cr) from CustomerRoom cr where cr.room.id = :rid AND  cr.check_in = :checkIn AND cr.check_out = :checkOut")
	int findNumberOfBookings(int rid, LocalDate checkIn, LocalDate checkOut);

	@Query("select c from CustomerRoom c where c.hotel.id=?1 and c.check_in=?2")
	List<CustomerRoom> findCustomerByHidAndDate(int hid, LocalDate date);

	@Query("select cb.customer from CustomerRoom cb where cb.hotel.id=?1")
	List<Customer> getCustomersVisited(int hid);

	@Query("select cb from CustomerRoom cb where cb.hotel.id=?1")
	List<CustomerRoom> getByHotel(int hid);

	

	

	

	
}
