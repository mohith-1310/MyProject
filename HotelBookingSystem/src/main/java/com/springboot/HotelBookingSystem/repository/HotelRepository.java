package com.springboot.HotelBookingSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.HotelAdmin;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{

	List<Hotel> findByExecutiveId(int eid);

	List<Hotel> findByLocationId(int lid);

	List<Hotel> findByHotelAdminId(int aid);

	@Query("select cr.hotel from CustomerRoom cr where cr.hotel.location.name=?1 and cr.check_in=?2 and cr.check_out=?3")
	List<Hotel> getHotelsByCinCoutLocation(String lname, LocalDate cin, LocalDate cout);

	@Query("select h from Hotel h where h.location.name=?1")
	List<Hotel> getHotelsByLocation(String lname);

	   

	

}
