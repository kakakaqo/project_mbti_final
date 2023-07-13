package com.javalab.mbti.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.javalab.mbti.dto.UserDTO;

@Service
public interface LoginService {
	 boolean authenticate(String userId, String userPassword);
	 	void loginUser(UserDTO user, HttpSession session);
	    void logoutUser(HttpSession session);
	    UserDTO getUserInfo(String userId);
}
