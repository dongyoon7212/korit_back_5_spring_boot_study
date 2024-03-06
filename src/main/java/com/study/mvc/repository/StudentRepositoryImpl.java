package com.study.mvc.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    List<String> studentList = List.of("전주환", "서창현", "예횽렬");

    @Override
    public List<String> getStudentListAll() {
        return studentList;
    }

    @Override
    public String findStudentNameByIndex(int index) {
        return studentList.get(index);
    }
}
