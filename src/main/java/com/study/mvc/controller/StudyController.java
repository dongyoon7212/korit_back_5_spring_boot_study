package com.study.mvc.controller;

import com.study.mvc.model.HelloModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StudyController {

    //등록된 주소가 없으면 404 오류가 뜬다

    //ModelAndView 는 맵으로 데이터 전달 가능
    //MVC
    @GetMapping("/hello")
    public String helloPage(Model model) {
        HelloModel helloModel = HelloModel.builder()
                .name1("이동윤")
                .name2("삼동윤")
                .name3("사동윤")
                .build();
        model.addAttribute("helloModel", helloModel);
        return "hello";
    }

    //그냥 String으로 리턴은 map으로 전달 불가능
    //주로 이 방법을 주로 쓰게 된다.
    //MVC가 아니라 REST
    @GetMapping("/test")
    @ResponseBody
    //ResponseBody를 쓰면 응답 데이터로 test를 리턴한 데이터를 출력하게 됨(데이터만 응답)
    //페이지를 리턴하는게 아니라 데이터(json)를 리턴해줌
    public Map<String, Object> testPage() {
        Map<String, Object> testObj = new HashMap<>();
        testObj.put("age", 26);
        return testObj;
    }
}
