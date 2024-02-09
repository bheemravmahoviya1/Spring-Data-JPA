package com.ora.spring.jpa.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ora.spring.jpa.dto.User;
import com.ora.spring.jpa.service.UserService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public String userSave(@RequestBody User user ) {
		log.info("user : {}",user);
		return this.userService.save(user);
	}
	
	@GetMapping
	public List<User> getUsers(){
		return this.userService.findAll();
	}

}
