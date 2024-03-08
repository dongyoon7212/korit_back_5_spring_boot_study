package com.study.mvc.repository;

import com.study.mvc.entity.Study;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// DB연결은 repository에 인터페이스 하나만 만든다.
@Mapper
public interface DBStudyRepository {
    public int save(Study study); // insert 리턴값은 int이다.
    public Study findStudyById(int id);
    public Study findStudyByName(String name);
    public List<Study> findAll();
    public int deleteById(int id);
}

//DTO -> Study(Entity) -> DB