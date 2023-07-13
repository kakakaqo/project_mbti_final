package com.javalab.mbti.controller;

import com.javalab.mbti.entity.Inquiry;
import com.javalab.mbti.service.InquiryService;
import com.javalab.mbti.service.InquiryServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @PostMapping("/submit")
    @ResponseBody
    public String submitInquiry(@ModelAttribute("inquiry") @Valid Inquiry inquiry,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "오류가 발생했습니다.";
        }

        inquiryService.saveInquiry(inquiry);

        return "문의가 성공적으로 제출되었습니다!";
    }
}