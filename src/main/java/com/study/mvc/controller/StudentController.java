package com.study.mvc.controller;

import com.study.mvc.dto.StudentReqDto;
import com.study.mvc.dto.StudentRespDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/student")
    // ? 와일드 카드인 이유, 뭐가 들어갈지 몰라 그냥 와일드카드로 퉁침
    public ResponseEntity<?> getStudentInfo(StudentReqDto studentReqDto) {
        System.out.println(studentReqDto);

        return ResponseEntity.badRequest().header("test", "header_test").body(studentReqDto.toRespDto());
    }
}