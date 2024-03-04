package com.study.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudyController {

    @GetMapping("/hello")
    public ModelAndView helloPage(Model model) {
        model.addAttribute("name1", "이동윤");
        model.addAttribute("name2", "삼동윤");
        model.addAttribute("name3", "사동윤");
        return new ModelAndView("hello");
    }

    @GetMapping("/test")
    public String testPage(Model model) {
        model.addAttribute("age", 26);
        return "test";
    }
}
