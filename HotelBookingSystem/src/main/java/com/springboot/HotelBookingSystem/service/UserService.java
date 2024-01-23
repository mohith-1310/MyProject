package com.springboot.HotelBookingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.repository.UserRepository;


@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;


	public  User insert(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("username called");
		User user = userRepository.findByUsername(username);
		System.out.println(user);
		return user;
	}
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}
	public User getById(int uid) throws InvalidIdException {
		
		Optional <User>optional = userRepository.findById(uid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("");
		}
		return optional.get();
	}
}
