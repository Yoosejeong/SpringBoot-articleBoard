package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){ // model 객체 받아오기
        model.addAttribute("username", "Leejiwoo" );

        return "greetings"; // greetings.mustache 파일 반환
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname", "이지우");
        return "goodbye";
    }
}
