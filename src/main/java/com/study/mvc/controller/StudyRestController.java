package com.study.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //무조건 데이터 응답, html을 만들 수 없음
public class StudyRestController {

    @GetMapping("/hello/test")
    public String hello() {
        return "Hello";
    }
}
