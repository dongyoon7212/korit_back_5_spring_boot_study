package com.study.mvc.service;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.dto.DBStudyInsertRespDto;
import com.study.mvc.dto.DBStudySelectRespDto;
import com.study.mvc.entity.Study;
import com.study.mvc.repository.DBStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DBStudyService {
    @Autowired //DBStudyRepository는 컴포넌트가 아니다, 하지만 실질적으로 study.xml이 컴포넌트가 되기떄문에 Autowired가 가능하다.
    private DBStudyRepository dbStudyRepository;

    public DBStudyInsertRespDto createStudy(DBStudyReqDto dbStudyReqDto) {

        // 첫번째 방법 [ AllArgs ]
//        Study study = new Study(0, dbStudyReqDto.getName(), dbStudyReqDto.getAge(), null);

        // 두번째 방법 [ NoArgs ]
//        Study study = new Study();
//        study.setName(dbStudyReqDto.getName());
//        study.setAge(dbStudyReqDto.getAge());

        // 세번째 방법 [ builder ]
        Study findStudy = dbStudyRepository.findStudyByName(dbStudyReqDto.getName());

        if(findStudy != null) {
            return DBStudyInsertRespDto.builder()
                    .successStatus(false)
                    .build();
        }

        Study study = Study.builder()
                .name(dbStudyReqDto.getName())
                .age(dbStudyReqDto.getAge())
                .build();



        int successCount = dbStudyRepository.save(study);

        DBStudyInsertRespDto dbStudyRespDto = DBStudyInsertRespDto.builder()
                .id(study.getId()) // db에 저장 후 생성된 키값이 study객체에 들어가있음
                .name(study.getName())
                .age(study.getAge())
                .successStatus(successCount > 0)
                .successCount(successCount)
                .build();
        return dbStudyRespDto;
    }

    public DBStudySelectRespDto findStudyById(int id) {
        Study study = dbStudyRepository.findStudyById(id);

        System.out.println(study);

        DBStudySelectRespDto dbStudySelectRespDto = DBStudySelectRespDto.builder()
                .id(study.getId())
                .name(study.getName())
                .age(study.getAge())
                .build();

        return dbStudySelectRespDto;
    }

    public DBStudySelectRespDto findStudyByName(String name) {
        Study study = dbStudyRepository.findStudyByName(name);

        return study == null ? null : study.toDto();
    }

    public List<DBStudySelectRespDto> findAll() {
        List<DBStudySelectRespDto> respDtoList = new ArrayList<>();
        List<Study> studyList = dbStudyRepository.findAll();

        for (Study study : studyList) {
            respDtoList.add(study.toDto());
        }

        return respDtoList;
    }

    public List<DBStudySelectRespDto> findAll2() {

        return dbStudyRepository.findAll()
                .stream()
                .map(Study::toDto)
                .collect(Collectors.toList());
    }

    public int deleteById(int id) {

        return dbStudyRepository.deleteById(id);
    }

    public int putById(int id, DBStudyReqDto dbStudyReqDto) {

        return dbStudyRepository.putById();
    }
}
