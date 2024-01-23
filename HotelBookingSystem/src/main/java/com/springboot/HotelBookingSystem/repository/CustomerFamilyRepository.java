package com.springboot.HotelBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.HotelBookingSystem.model.CustomerFamily;

public interface CustomerFamilyRepository extends JpaRepository<CustomerFamily, Integer>{

}
