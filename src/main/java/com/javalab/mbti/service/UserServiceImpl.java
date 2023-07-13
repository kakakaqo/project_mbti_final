package com.javalab.mbti.service;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.javalab.mbti.dto.UserDTO;
import com.javalab.mbti.entity.User;
import com.javalab.mbti.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User register(UserDTO dto) {
		User entity = dtoToEntity(dto);
		return userRepository.save(entity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();

		/* 유효성 및 중복 검사에 실패한 필드 목록을 받음 */
		for (FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}

		return validatorResult;
	}
	

}
