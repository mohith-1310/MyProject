package com.springboot.HotelBookingSystem.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.springboot.HotelBookingSystem.enumerate.Gender;

public class Customerdto {
		private String name;
		private String phone;
		private LocalDate dateOfBirth;
		private int age;
		@Enumerated(EnumType.STRING)
		private Gender gender;
		private String email;
		private String idproof;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		public LocalDate getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public Gender getGender() {
			return gender;
		}
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getIdproof() {
			return idproof;
		}
		public void setIdproof(String idproof) {
			this.idproof = idproof;
		}
		
		
}
