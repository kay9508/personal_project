package com.sy.personal_project.controller;

import com.sy.personal_project.entity.Board;
import com.sy.personal_project.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public String test(Model model) {

        Board load = testService.boardLoad();

        model.addAttribute("load", load);

        return "/test";
    }

    @GetMapping("/test2")
    public String test2(Model model) {

        return "/test2";
    }
}
