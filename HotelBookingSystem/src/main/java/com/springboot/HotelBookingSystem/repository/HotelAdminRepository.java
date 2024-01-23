package com.springboot.HotelBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.HotelBookingSystem.model.HotelAdmin;

public interface HotelAdminRepository extends JpaRepository<HotelAdmin, Integer>{

	List<HotelAdmin> findByExecutiveId(int eid);

	@Query("select h from HotelAdmin h where h.user.id=?1")
	HotelAdmin findByUserId(int uid);
	
	

}
