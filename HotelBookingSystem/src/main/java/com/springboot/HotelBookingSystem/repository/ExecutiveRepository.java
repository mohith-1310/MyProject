package com.springboot.HotelBookingSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.HotelBookingSystem.model.Executive;

public interface ExecutiveRepository extends JpaRepository<Executive, Integer>{

	@Query("select e from Executive e where e.user.id=?1")
	Executive findByUserId(int uid);

}
