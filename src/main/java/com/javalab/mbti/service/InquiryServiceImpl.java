package com.javalab.mbti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.mbti.entity.Inquiry;
import com.javalab.mbti.repository.InquiryRepository;

@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

    @Autowired
    public InquiryServiceImpl(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @Override
    public void saveInquiry(Inquiry inquiry) {
        inquiryRepository.save(inquiry);
    }
}