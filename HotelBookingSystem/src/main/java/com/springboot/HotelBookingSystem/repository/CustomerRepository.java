package com.springboot.HotelBookingSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.HotelBookingSystem.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("select c.customer.name from CustomerRoom c where c.room.hotel.id=?1 and c.check_in=?2")
	List<Customer> findCustomerByHidAndDate(int hid, LocalDate date);

	@Query("select c from Customer c where c.user.id=?1")
	Customer findByUserId(int uid);

}
