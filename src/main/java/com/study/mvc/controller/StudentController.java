package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.dto.StudentReqDto;
import com.study.mvc.entity.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StudentController {

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@CookieValue(required = false) String students, @RequestBody Student student) throws JsonProcessingException, UnsupportedEncodingException {
        //@RequestBody json을 객체로 변환해준다. post는 @RequestBody 무조건 쓴다.
        //하지만 json이 아니라 x-www-form-urlencoded로 데이터를 보낼땐 없어야 한다.
        ObjectMapper objectMapper = new ObjectMapper();
        List<Student> studentList = new ArrayList<>();
        int lastId = 0;

        if(students != null) {
            if(!students.isBlank()) {
                for(Object object : objectMapper.readValue(students, List.class)){ // readValue json을 list로 바꾼다. => 바꾸면 Object타입으로 되어있다.
                    Map<String, Object> studentMap = (Map<String, Object>) object; // object를 Map으로 다운캐스팅한다.
                    studentList.add(objectMapper.convertValue(studentMap, Student.class)); // convertValue Map을 student객체로 바꾼다. student객체를 list로 넣게 된다.
                }
                lastId = studentList.get(studentList.size() - 1).getStudentId(); // list의 마지막 index값을 가져와 1을 빼고 해당 객체의 id를 가져온다.
            }
        }

        student.setStudentId(lastId + 1);
        studentList.add(student);

        String studentListJson = objectMapper.writeValueAsString(studentList); // writeValueAsString 객체를 json으로 바꾼다.

        ResponseCookie responseCookie = ResponseCookie
                .from("students", URLEncoder.encode(studentListJson, "UTF-8")) // 큰따옴표떄문에 인코딩해야한다.
                .httpOnly(true) // http에서 사용
                .secure(true) // 암호화
                .path("/") // 쿠키를 사용할 경로, 설정된 경로에서만 쿠키가 사용된다., /로 하면 루트로 설정됨(모든 경로)
                .maxAge(60) // 유지되는 시간(60분)
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