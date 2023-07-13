package com.javalab.mbti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javalab.mbti.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	boolean existsByUserId(String userId);
	
	User findByUserId(String userId);

}
