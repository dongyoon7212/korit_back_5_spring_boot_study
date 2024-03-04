package com.study.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class StudyController {

    //ModelAndView 는 맵으로 데이터 전달 가능
    @GetMapping("/hello")
    public ModelAndView helloPage() {
        Map<String, Object> model = new HashMap<>();
        model.put("name1", "이동윤");
        model.put("name2", "삼동윤");
        model.put("name3", "사동윤");
        return new ModelAndView("hello", model);
    }

    //그냥 String으로 리턴은 map으로 전달 불가능
    @GetMapping("/test")
    public String testPage(Model model) {
        model.addAttribute("age", 26);
        return "test";
    }
}
