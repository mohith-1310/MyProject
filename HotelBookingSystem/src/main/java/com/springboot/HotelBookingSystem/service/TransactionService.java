package com.springboot.HotelBookingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Transaction;
import com.springboot.HotelBookingSystem.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Transaction insert(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public List<Transaction> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return transactionRepository.findAll(pageable).getContent();
	}
}
