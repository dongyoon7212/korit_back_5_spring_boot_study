package com.study.mvc.controller;

import com.study.mvc.dto.StudentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/student")
    public StudentDto getStudentInfo(StudentDto studentDto) {
        System.out.println(studentDto);
        return studentDto;
    }
}
