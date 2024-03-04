package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.dto.StudentReqDto;
import com.study.mvc.entity.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StudentController {

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@CookieValue String students, @RequestBody Student student) throws JsonProcessingException {
        //@RequestBody json을 객체로 변환해준다. post는 @RequestBody 무조건 쓴다.
        //하지만 json이 아니라 x-www-form-urlencoded로 데이터를 보낼땐 없어야 한다.

        List<Student> studentList = new ArrayList<>();
        int lastId = 0;

        if(students != null) {
            if(!students.isBlank()) {
                ObjectMapper studentsCookie = new ObjectMapper();
                studentList = studentsCookie.readValue(students, List.class);
                lastId = studentList.get(studentList.size() - 1).getStudentId();
            }
        }

        student.setStudentId(lastId + 1);
        studentList.add(student);
        ObjectMapper newStudentList = new ObjectMapper();
        String newStudents = newStudentList.writeValueAsString(studentList);
        ResponseCookie responseCookie = ResponseCookie
                .from("studentCookie", newStudents)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                .domain("localhost:8080")
                .build();
        return ResponseEntity
                .created(null)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(student);
    }

    @GetMapping("/student")
    // ? 와일드 카드인 이유, 뭐가 들어갈지 몰라 그냥 와일드카드로 퉁침
    public ResponseEntity<?> getStudentInfo(StudentReqDto studentReqDto) {
        System.out.println(studentReqDto);

        return ResponseEntity.badRequest().header("test", "header_test").body(studentReqDto.toRespDto());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable int studentId) { //주소 안의 값, 아예 생략은 불가
        List<Student> studentList = List.of(
                new Student(1, "이동윤"),
                new Student(2, "삼동윤"),
                new Student(3, "사동윤"),
                new Student(4, "오동윤")
        );
//        Student findStudent = null;
//        for(Student student : studentList){
//            if(student.getStudentId() == studentId){
//                findStudent = student;
//            }
//        }
//        if(optionalStudent == null) {
//            return ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 ID 입니다."));
//        }
        Optional<Student> optionalStudent = studentList.stream().filter(student -> student.getStudentId() == studentId).findFirst();
        Student findStudent = null;
        if(optionalStudent.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 ID 입니다."));
        }
        findStudent = optionalStudent.get();

        return ResponseEntity.ok().body(findStudent);
    }
}