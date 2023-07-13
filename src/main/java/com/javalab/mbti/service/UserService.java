package com.javalab.mbti.service;


import java.util.Map;

import org.springframework.validation.Errors;

import com.javalab.mbti.dto.UserDTO;
import com.javalab.mbti.entity.User;


public interface UserService {
	
	User register(UserDTO userDTO);
	
	default User dtoToEntity(UserDTO userDTO) {
		User entity = User.builder()
				.userId(userDTO.getUserId())
				.userPassword(userDTO.getUserPassword())
				.userEmail(userDTO.getUserEmail())
				.userName(userDTO.getUserName())
				.build();
		return entity;
	}
	
	default UserDTO entityToDto(User user) {
		UserDTO dto = UserDTO.builder()
				.userId(user.getUserId())
				.userPassword(user.getUserPassword())
				.userEmail(user.getUserEmail())
				.userName(user.getUserName())
				.regDate(user.getRegDate()) 
				.build();
		return dto;
	}
	
	Map<String, String> validateHandling(Errors errors);

	
	
}
