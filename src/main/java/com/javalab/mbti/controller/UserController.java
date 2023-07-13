package com.javalab.mbti.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javalab.mbti.dto.UserDTO;
import com.javalab.mbti.entity.User;
import com.javalab.mbti.service.UserService;
import com.javalab.mbti.validator.CheckIdValidator;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
	
	private final UserService userService;
	private final CheckIdValidator checkIdValidator;
	
	@InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkIdValidator);
    }
	
	@Autowired
	public UserController(UserService userService, CheckIdValidator checkIdValidator) {
		this.userService = userService;
		this.checkIdValidator = checkIdValidator;
	}
	
	// GetMapping 뭐가 맞는지 몰라서 주석처리
	/*
	 * @GetMapping("/register") public String registerForm(Model model) {
	 * model.addAttribute("userDTO", new UserDTO()); return "user/register"; }
	 */

	
	@GetMapping("/register")
	public void registerForm(@ModelAttribute("userDTO") UserDTO userDTO,
    							BindingResult bindingResult,Model model) {
		model.addAttribute("userDTO", new UserDTO());
		model.addAttribute("duplicate", false); // duplicate 값을 false로 초기화
		//model.addAttribute("errorMessage", "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.");

	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@ModelAttribute @Valid UserDTO userDTO,
	                                       BindingResult bindingResult,
	                                       Model model) {
	    log.info("register process userDTO.toString()" + userDTO.toString());
	    
	    // 아이디 중복 체크 이 부분 다시 해 볼것
	   checkIdValidator.validate(userDTO, bindingResult);
	    
	    if (bindingResult.hasErrors()) {
	        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
	        for (FieldError error : fieldErrors) {
	            log.error(error.getField() + " " + error.getDefaultMessage());
	        }
	        
	        model.addAttribute("userDTO", userDTO);
	        model.addAttribute("errorMessage", "이미 사용 중인 아이디 입니다.");

	        // 필드 에러를 모델에 추가
	        model.addAttribute("fieldErrors", fieldErrors);

	        Map<String, Object> response = new HashMap<>();
	        response.put("success", false);
	        response.put("duplicate", true);
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	    }

	    userService.register(userDTO);
	    log.info("Registration successful");
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("success", true);
	    response.put("duplicate", false);
	    return ResponseEntity.ok().body(response);
	}

}