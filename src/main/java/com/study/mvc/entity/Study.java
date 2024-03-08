package com.study.mvc.entity;

import com.study.mvc.dto.DBStudySelectRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// noargs, allargs는 항상 있어야함
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Study { // 클래스의 이름은 db 테이블명과 일치 시킨다. , 칼럼도 일치시킴
    private int id;
    private String name;
    private int age;
    private LocalDateTime createDate;

    public DBStudySelectRespDto toDto() {
        return DBStudySelectRespDto.builder()
                .id(id)
                .name(name)
                .age(age)
                .build();
    }
}
