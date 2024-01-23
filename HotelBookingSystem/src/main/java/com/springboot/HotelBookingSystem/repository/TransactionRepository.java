package com.springboot.HotelBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.HotelBookingSystem.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
