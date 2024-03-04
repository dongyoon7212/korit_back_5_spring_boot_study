package com.study.mvc.controller;

import com.study.mvc.dto.HelloDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller명: StudentController
 *
 * 메소드명: getStudentInfo()
 * 요청 메소드: Get
 * 요청 URL: /student
 * 요청 params: name, age, phone, address
 * 응답데이터: JSON(name, age, phone, address)
 */

@RestController //무조건 데이터 응답, html을 만들 수 없음
public class StudyRestController {

    @GetMapping("/hello/test")
    public String hello(HelloDto helloDto) { // request.getParams와 같다. , 변수명과 키값을 일치
        // int로 해도 자동으로 String으로 형변환이 일어나서 가능
        System.out.println(helloDto);
        return "Hello";
    }
}