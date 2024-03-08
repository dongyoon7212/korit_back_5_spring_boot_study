package com.study.mvc.service;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.repository.DBStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBStudyService {
    @Autowired //DBStudyRepository는 컴포넌트가 아니다, 하지만 실질적으로 study.xml이 컴포넌트가 되기떄문에 Autowired가 가능하다.
    private DBStudyRepository dbStudyRepository;

    public int createStudy(DBStudyReqDto dbStudyReqDto) {
        dbStudyRepository.save()
    }
}
