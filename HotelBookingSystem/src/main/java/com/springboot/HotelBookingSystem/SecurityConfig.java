package com.springboot.HotelBookingSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.HotelBookingSystem.service.UserService;




@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserService userService;
	
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	System.out.println("config called");
	auth.authenticationProvider(getProvider());
}
@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
	.authorizeRequests()
	.antMatchers(HttpMethod.POST,"/auth/login").authenticated()
	.antMatchers("/feelhome/customer/add").permitAll()
	.and().httpBasic()
	.and()
	.csrf().disable()
	.cors().disable();
		
	}
@Bean
public PasswordEncoder getEncoder() {
	return new BCryptPasswordEncoder();
}

@Bean
public Logger getLogger() {
	return LoggerFactory.getLogger("Log Records");
}

public AuthenticationProvider getProvider() {
	DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
	dao.setPasswordEncoder(getEncoder());
	dao.setUserDetailsService(userService);
	return dao;
}
}
