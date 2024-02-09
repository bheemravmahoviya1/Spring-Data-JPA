package com.ora.spring.transactional.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long id;
	
	private String userName;
	
	private String password;
	
	private boolean isEnabled;

	
}