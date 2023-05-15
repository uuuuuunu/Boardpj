package ptp.myboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ptp.myboard.domain.Member;
import ptp.myboard.repository.MemberReposirory;
import ptp.myboard.service.MemberService;

import javax.validation.Valid;

@Controller
@RequestMapping("/yw")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberReposirory memberReposirory;
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @GetMapping
    public String home(){
        return "basic/login/Home";
    }

    @GetMapping("/test")
    public String test(){
        return "basic/index";
    }
    @GetMapping("/join")
    public String JoinForm(@ModelAttribute("member")Member member){
        return "basic/login/AddMember";
    }

    @PostMapping("/join")
    public String Join(@ModelAttribute("member") @Valid Member member, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "basic/login/AddMember";
        }
        String rawPassword=member.getPassword();
        String encPassword=passwordEncoder.encode(rawPassword);
        member.setPassword(encPassword);
        memberReposirory.save(member);
        return "redirect:/yw";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "basic/login/LoginForm";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "redirect:/yw/login";
    }



}
