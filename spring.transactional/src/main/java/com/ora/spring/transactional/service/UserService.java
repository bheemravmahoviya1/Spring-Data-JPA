package com.ora.spring.transactional.service;

import java.util.List;

import com.ora.spring.transactional.dto.User;



public interface UserService {
	
	public String save(User user);
	
	public List<User> findAll();

}
