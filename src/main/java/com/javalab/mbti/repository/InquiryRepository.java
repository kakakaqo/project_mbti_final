package com.javalab.mbti.repository;

import com.javalab.mbti.entity.Inquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, String> {
	
}