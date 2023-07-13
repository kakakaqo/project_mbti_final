package com.javalab.mbti.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.javalab.mbti.service.UserService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Integer boardId;
    
    @NotNull
    private String boardContent;

    @Builder.Default
    private Integer boardHit = 0;

    private LocalDateTime regDate = LocalDateTime.now();
    //private LocalDateTime modDate;

    @NotNull
    private String userId;
    
    private String mbtiName;
    
}