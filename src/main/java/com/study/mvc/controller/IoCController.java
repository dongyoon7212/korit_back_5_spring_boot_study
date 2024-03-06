package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.mvc.diAndIoc.IoCService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IoCController {

    // autowired를 썼을때 이점
    //
    @Autowired
    private IoCService ioCService;

    @GetMapping("/ioc")
    public ResponseEntity<?> iocTest() throws JsonProcessingException {
        String json = ioCService.getJson();

        return ResponseEntity.ok(json);
    }
}
