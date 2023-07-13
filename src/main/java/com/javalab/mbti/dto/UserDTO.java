package com.javalab.mbti.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	 @NotBlank
	 @Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
	 private String userId;

	 @NotBlank
	 @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
	 private String userPassword;

	 @NotBlank
	 @Email(message = "이메일 형식에 맞게 작성해주세요.")
	 private String userEmail;
	 
	 @NotBlank
	 @Size(min = 2, max = 50, message = "이름은 2자 ~ 50자 이내로 입력하세요.")
	 private String userName;

	 @Builder.Default
//	 @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	 private LocalDateTime regDate = LocalDateTime.now();
	 
//	 public boolean hasErrors(String userId) {
//		 if (userId.equals("userId")) {
//	            return StringUtils.isEmpty(userId);
//	 }
//		 return false;
//	 }

}
