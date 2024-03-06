package com.study.mvc.service;

import java.util.List;

public interface StudentService {
    public List<String> getStudentList();
    public String getStudent(int index);
}
