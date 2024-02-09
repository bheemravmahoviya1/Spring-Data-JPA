package com.ora.spring.transactional.service;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ora.spring.transactional.dto.User;
import com.ora.spring.transactional.entity.UserEntity;
import com.ora.spring.transactional.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * We are demonstrating the examples of transaction management when @Transactional annotation added.
	 * PROPAGATION  = Progagation.PROPAGATION_REQUIRED (Default )
	 * ISOLATION = ISOLATION_DEFAULT
	 * 
	 * Note: A transaction will be created here and in userRepository.save(-) method @transaction annotation 
	 * it will not create any new transaction and use the same transaction which created in our service layer.
	 * 
	 * Note: Transaction will only enable for that method called by using its instantiated only.
	 * 		 Mean all static, private or public method which is called from same call will not participate 
	 * 		 in transaction or create new transaction. 
	 */	
	
	@Override
	@Transactional
	public String save(User user) {
	//	log.info("user start: {}",user);
		String msg = "User saved successfully.";
		

			this.saveUser(user);
		
		
	//	log.info("user end: {}",user);
		return msg;
	}

	
	 
	/**
	 * We are demonstrating the examples of transaction management.
	 * Propagation  = Progagation.REQUIRED
	 * Note: Transaction will only enable for that method called by using its instantiated only.
	 * 		 Mean all static, private or public method which is called from same call will not participate 
	 * 		 in transaction or create new transaction. Even we have add @Transactional in below method but 
	 *       no new transaction will be create here.
	 */	
	@Transactional
	private void saveUser(User user) {
		UserEntity entity =new UserEntity();
		entity.setUserName(user.getUserName());
		entity.setPassword(user.getPassword());
		entity.setCreatedOn(Instant.now());
		entity.setUpdatedOn(Instant.now());
		entity.setEnabled(false);
		this.userRepository.save(entity);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		List<UserEntity> userEntities= this.userRepository.findAll();
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
