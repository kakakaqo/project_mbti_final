package com.javalab.mbti.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/map")
public class MapController {

	@Value("${kakaoMap_key}")
	private String kakaoMapKey;

	@GetMapping("/map")
	public String index(Model model) throws IOException {

		model.addAttribute("kakaoMapKey", kakaoMapKey);
		return "map/map";
	}
}