package com.javalab.mbti.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.javalab.mbti.dto.UserDTO;
import com.javalab.mbti.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckIdValidator extends AbstractValidator<UserDTO>{
	
	private final UserRepository userRepository;
	
	@Override
	protected void doValidate(UserDTO dto, Errors errors) {
		validateUserId(dto, errors);
	}
	
	public void validateUserId(UserDTO dto, Errors errors) {
		if (userRepository.existsByUserId(dto.getUserId())) {
			errors.rejectValue("userId", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
		}
	}
	
	// 아이디 중복 체크 메서드를 추가합니다.
    public boolean validate(UserDTO userDTO, Errors errors) {
        validateUserId(userDTO, errors);
        return !errors.hasErrors(); // 오류가 없으면 true를 반환합니다.
    }

}
