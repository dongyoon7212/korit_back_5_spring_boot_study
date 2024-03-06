package com.study.mvc.service;

import com.study.mvc.dto.StudentExDto;
import com.study.mvc.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

//    @Override
//    public List<String> getStudentList() {
//        return studentRepository.getStudentListAll();
//    }
//
//    @Override
//    public String getStudent(int index) {
//        return studentRepository.findStudentNameByIndex(index);
//    }

    @Override
    public List<?> getStudentList() {
        List<StudentExDto> studentDtoList = new ArrayList<>();
        List<String> studentList = studentRepository.getStudentListAll();

        for(String studentName : studentList) {
            studentDtoList.add(new StudentExDto(studentName));
        }

        return studentDtoList;
    }

    @Override
    public Object getStudent(int index) {
        String studentName = studentRepository.findStudentNameByIndex(index);
        return new StudentExDto(studentName);
    }
}
