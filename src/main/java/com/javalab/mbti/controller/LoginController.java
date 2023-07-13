package com.javalab.mbti.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javalab.mbti.dto.UserDTO;
import com.javalab.mbti.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@GetMapping("/form")
	public String loginForm() {
		return "user/register";
	}


	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<?> login(@RequestParam("userId") String userId,
	                               @RequestParam("userPassword") String userPassword,
	                               HttpSession session) {
	    if (loginService.authenticate(userId, userPassword)) {
	        UserDTO user = loginService.getUserInfo(userId);
	        loginService.loginUser(user, session);
	        return ResponseEntity.ok().body("{\"success\": true}");
	    } else {
	        // 인증 실패 처리
	        log.error("인증 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
	        return ResponseEntity.ok().body("{\"success\": false}");
	    }
	}




	@GetMapping("/logout")
	public String logout(HttpSession session) {
		loginService.logoutUser(session);
		return "redirect:/"; // 로그아웃 후 이동할 페이지 경로
	}
}
