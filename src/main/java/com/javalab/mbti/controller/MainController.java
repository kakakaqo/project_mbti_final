package com.javalab.mbti.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @GetMapping("/getSessionInfo")
  public ResponseEntity<Map<String, Object>> getSessionInfo(HttpServletRequest request) {
    // 세션 정보를 확인하는 로직
    boolean isLoggedIn = false;
    HttpSession session = request.getSession(false);
    if (session != null && session.getAttribute("loggedInUser") != null) {
      isLoggedIn = true;
    }

    // 세션 정보를 JSON 형식으로 반환
    Map<String, Object> response = new HashMap<>();
    response.put("isLoggedIn", isLoggedIn);
    return ResponseEntity.ok(response);
  }

}