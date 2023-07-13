package com.javalab.mbti.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mbti")
public class MbtiController {

    @PostMapping("/info")
    public ResponseEntity<String> infoMbti(@RequestParam("mbti") String mbti) {
        // 받아온 mbti 값을 비교하고 필요한 처리를 수행합니다.
        if (mbti.equals("ISTJ")) {
            // mbti 값이 "ISTJ"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ISTJ.png");
        } else if (mbti.equals("ISFJ")) {
            // mbti 값이 "ISFJ"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ISFJ.png");
        } else if (mbti.equals("INFJ")) {
            // mbti 값이 "INFJ"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/INFJ.png");
        } else if (mbti.equals("INTJ")) {
            // mbti 값이 "INTJ"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/INTJ.png");
        } else if (mbti.equals("ISTP")) {
            // mbti 값이 "ISTP"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ISTP.png");
        } else if (mbti.equals("ISFP")) {
            // mbti 값이 "ISFP"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ISFP.png");
        } else if (mbti.equals("INFP")) {
            // mbti 값이 "INFP"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/INFP.png");
        } else if (mbti.equals("INTP")) {
            // mbti 값이 "INTP"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/INTP.png");
        } else if (mbti.equals("ESTP")) {
            // mbti 값이 "ESTP"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ESTP.png");
        } else if (mbti.equals("ESFP")) {
            // mbti 값이 "ESFP"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ESFP.png");
        } else if (mbti.equals("ENFP")) {
            // mbti 값이 "ENFP"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ENFP.png");
        } else if (mbti.equals("ENTP")) {
            // mbti 값이 "ENTP"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ENTP.png");
        } else if (mbti.equals("ESTJ")) {
            // mbti 값이 "ESTJ"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ESTJ.png");
        } else if (mbti.equals("ESFJ")) {
            // mbti 값이 "ESFJ"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ESFJ.png");
        } else if (mbti.equals("ENFJ")) {
            // mbti 값이 "ENFJ"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ENFJ.png");
        } else if (mbti.equals("ENTJ")) {
            // mbti 값이 "ENTJ"와 일치하는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("assets/img/info/ENTJ.png");
        } else {
            // mbti 값이 위의 조건과 일치하지 않는 경우에 대한 처리를 수행합니다.
            return ResponseEntity.ok("처리결과 없음");
        }
    }
}
