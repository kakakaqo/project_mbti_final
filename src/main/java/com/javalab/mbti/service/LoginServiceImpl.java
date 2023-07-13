package com.javalab.mbti.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.mbti.dto.UserDTO;
import com.javalab.mbti.entity.User;
import com.javalab.mbti.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	 @Autowired
	    private UserRepository userRepository;
	 
	 	@Autowired
	    public LoginServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public boolean authenticate(String userId, String userPassword) {
	        User user = userRepository.findByUserId(userId);
	        if (user != null && user.getUserPassword().equals(userPassword)) {
	            return true;
	        }
	        return false;
	    }

	    @Override
	    public void loginUser(UserDTO user, HttpSession session) {
	        session.setAttribute("userId", user.getUserId());
	        session.setAttribute("userName", user.getUserName());
	    }
	    

	    @Override
	    public UserDTO getUserInfo(String userId) {
	    	User user = userRepository.findByUserId(userId);
	        if (user != null) {
	            // User 정보를 UserDTO로 변환하여 반환
	            return UserDTO.builder()
	                    .userId(user.getUserId())
	                    .userName(user.getUserName())
	                    // 다른 필드도 필요한 경우 추가
	                    .build();
	        }
	        return null; // 사용자 정보가 없는 경우 null 반환하거나 예외 처리
	    }
	    
	    @Override
	    public void logoutUser(HttpSession session) {
	        session.invalidate();
	    }
	}
