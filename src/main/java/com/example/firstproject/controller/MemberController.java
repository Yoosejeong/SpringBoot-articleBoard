package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/signup")
    public String MemberSignUp() {
        return "/members/new";
    }

    @PostMapping("/join")
    public String CreateSignUp(MemberForm form){
        // System.out.println(form.toString());
        log.info(form.toString());
        //DTO를 entity로 변환
        Member member = form.toEntity();
        //System.out.println(member.toString());
        log.info(member.toString());
        //repository
        Member saved = memberRepository.save(member);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "";
    }
}
