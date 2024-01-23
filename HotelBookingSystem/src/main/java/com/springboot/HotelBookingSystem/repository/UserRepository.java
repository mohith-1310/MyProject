package com.springboot.HotelBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.HotelBookingSystem.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
