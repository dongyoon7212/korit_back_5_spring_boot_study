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

        return ResponseEntity.ok(null);
    }
}
