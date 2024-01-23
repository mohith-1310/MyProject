package com.springboot.HotelBookingSystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.model.Transaction;
import com.springboot.HotelBookingSystem.service.TransactionService;

@RestController
@RequestMapping("/feelhome")
@CrossOrigin(origins = {"http://localhost:3000"})
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transaction/add")
	public Transaction insert(@RequestBody Transaction transaction) {
		/*insert transaction into db*/
		transaction.setDate(LocalDate.now());
		return transactionService.insert(transaction);
	}
	
	@GetMapping("/transaction/getall")
	public List<Transaction> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size)
	{
		Pageable pageable = PageRequest.of(page,size);
		return transactionService.getAll(pageable);
		
		
		
		}
}
