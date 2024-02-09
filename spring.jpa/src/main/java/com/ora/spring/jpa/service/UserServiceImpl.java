package com.ora.spring.jpa.service;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ora.spring.jpa.dto.User;
import com.ora.spring.jpa.entity.UserEntity;
import com.ora.spring.jpa.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public String save(User user) {
		log.info("user start: {}",user);
		String msg = "User saved successfully.";
		UserEntity entity =new UserEntity();
		entity.setUserName(user.getUserName());
		entity.setPassword(user.getPassword());
		entity.setCreatedOn(Instant.now());
		entity.setUpdatedOn(Instant.now());
		entity.setEnabled(false);
		try {
			userRepository.save(entity);
		
		}catch (Exception e) {
			msg = "User failed to save.";
			log.error("user exception: {}",e);
		}
		log.info("user end: {}",user);
		return msg;
	}


	@Override
	public List<User> findAll() {
		List<UserEntity> userEntities= this.userRepository.findAll();
		System.out.print(userEntities);
		Function<UserEntity,User>  userEntityToDtoTransform = userEnity -> {
			User user=new User();
			user.setId(userEnity.getId());
			user.setUserName(userEnity.getUserName());
			user.setEnabled(userEnity.isEnabled());
			return user;
		};
		
		return userEntities.stream().map(userEntityToDtoTransform).collect(Collectors.toList());
	}
	
	
	
	

}
