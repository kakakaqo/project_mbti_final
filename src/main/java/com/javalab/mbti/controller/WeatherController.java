package com.javalab.mbti.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Value("${weather_key}")
    private String weatherKey;

    
    
    @GetMapping("/city")
    public String index(Model model) throws IOException {
    	
        model.addAttribute("weatherKey", weatherKey);
        
        return "weather/weather";
    }
}