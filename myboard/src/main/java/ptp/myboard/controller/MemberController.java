package ptp.myboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @GetMapping
    public String home(){
        return "basic/login/home";
    }

    @GetMapping("/test")
    public String test(){
        return "basic/index";
    }
    @GetMapping("/join")
    public String JoinForm(@ModelAttribute("member")Member member){
        return "basic/login/addmember";
    }

    @PostMapping("/join")
    public String Join(@Valid @ModelAttribute("member") Member member, BindingResult memberBindingResult, Model model){
        if(memberBindingResult.hasErrors()){
            return "basic/login/addmember";
        }
        String rawPassword=member.getPassword();
        member.setOrgpassword(rawPassword);
        String encPassword=passwordEncoder.encode(rawPassword);
        member.setPassword(encPassword);
        memberService.memberSave(member);
        log.info("username={}",member.getUsername());
        return "redirect:/yw";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "basic/login/loginform";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "redirect:/yw/login";
    }
}
