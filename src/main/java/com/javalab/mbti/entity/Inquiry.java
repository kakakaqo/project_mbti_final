package com.javalab.mbti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Inquiry extends BaseEntity {
	
	@Id
	@Column(length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer inqId;
	
	@Column(length = 20, nullable = false)
	private String inqName;
	
	@Column(length = 100, nullable = false)
	private String inqEmail;
	
	@Column(length = 100, nullable = false)
	private String inqPhone;
	
	@Column(length = 3000, nullable = false)
	private String inqMessage;
}