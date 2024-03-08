package com.study.mvc.service;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.entity.Study;
import com.study.mvc.repository.DBStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBStudyService {
    @Autowired //DBStudyRepository는 컴포넌트가 아니다, 하지만 실질적으로 study.xml이 컴포넌트가 되기떄문에 Autowired가 가능하다.
    private DBStudyRepository dbStudyRepository;

    public int createStudy(DBStudyReqDto dbStudyReqDto) {

        // 첫번째 방법 [ AllArgs ]
//        Study study = new Study(0, dbStudyReqDto.getName(), dbStudyReqDto.getAge(), null);

        // 두번째 방법 [ NoArgs ]
//        Study study = new Study();
//        study.setName(dbStudyReqDto.getName());
//        study.setAge(dbStudyReqDto.getAge());

        // 세번째 방법 [ builder ]
        Study study = Study.builder()
                .name(dbStudyReqDto.getName())
                .age(dbStudyReqDto.getAge())
                .build();

        return dbStudyRepository.save(study);
    }
}
