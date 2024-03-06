package com.study.mvc.controller;

import com.study.mvc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentRestController {

    @Autowired
    @Qualifier("studentServiceImpl")
    private StudentService studentService;

//    @GetMapping("/ex/students")
//    public ResponseEntity<?> getStudentAll() {
//        List<String> students = studentService.getStudentList();
//        Map<String, String> studentMap = new HashMap<>();
//        List<Map<String, String>> getJson = new ArrayList<>();
//        for(String student : students) {
//            studentMap.put("name", student);
//            getJson.add(studentMap);
//        }
//
//        return ResponseEntity.ok().body(getJson);
//    }
//
//    @GetMapping("/ex/student/0")
//    public ResponseEntity<?> getStudent() {
//        Map<String, String> student = new HashMap<>();
//        String findStudent = studentService.getStudent(0);
//        student.put("name", findStudent);
//
//        return ResponseEntity.ok().body(student);
//    }
    @GetMapping("/ex/students")
    public ResponseEntity<?> students() {

        return ResponseEntity.ok(studentService.getStudentList());
    }

    @GetMapping("/ex/student/{index}")
    public ResponseEntity<?> student(@PathVariable int index) {

        return ResponseEntity.ok(studentService.getStudent(index));
    }


}
