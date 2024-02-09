package com.ora.spring.jpa.service;

import java.util.List;

import com.ora.spring.jpa.dto.User;

public interface UserService {
	
	public String save(User user);
	
	public List<User> findAll();

}
