package com.study.mvc.controller;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.service.DBStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DBController {

    @Autowired
    private DBStudyService dbStudyService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody DBStudyReqDto dbStudyReqDto) {

        return ResponseEntity.ok(dbStudyService.createStudy(dbStudyReqDto));
    }

    @GetMapping("/select/study/{id}")
    public ResponseEntity<?> selectId(@PathVariable int id) {

        return ResponseEntity.ok(dbStudyService.findStudyById(id));
    }

    @GetMapping("/select/study") //?name=이동윤
    public ResponseEntity<?> selectStudy(@RequestParam String name) {

        return ResponseEntity.ok(dbStudyService.findStudyByName(name));
    }

    @GetMapping("/select/studys")
    public ResponseEntity<?> selectAll() {

        return ResponseEntity.ok(dbStudyService.findAll());
    }

    @DeleteMapping("/delete/study/{id}")
    public ResponseEntity<?> deleteStudy(@PathVariable int id) {

        return ResponseEntity.ok(dbStudyService.deleteById(id));
    }

    //update는 두가지 방법이 있음
    //1. PutMapping (전체수정) {nickname: aaa, password: 1234} => {nickname: "", password: 1111}
    @PutMapping("/update/study/{id}")
    public ResponseEntity<?> putStudy(
            @PathVariable int id,
            @RequestBody DBStudyReqDto dbStudyReqDto) {

        return ResponseEntity.ok(dbStudyService.putById(id, dbStudyReqDto));
    }

    //2. PatchMapping (부분수정) {nickname: aaa, password: 1234} => {nickname: "", password: 1111} => {nickname: aaa, password: 1111}
    @PatchMapping("/update/study/{id}")
    public ResponseEntity<?> patchStudy(
            @PathVariable int id,
            @RequestBody DBStudyReqDto dbStudyReqDto) {

        return ResponseEntity.ok(dbStudyService.patchById(id, dbStudyReqDto));
    }
}