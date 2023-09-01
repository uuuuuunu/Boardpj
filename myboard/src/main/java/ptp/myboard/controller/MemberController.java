package ptp.myboard.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ptp.myboard.domain.Member;
import ptp.myboard.service.MemberService;


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

    @GetMapping("/join")
    public String JoinForm(@ModelAttribute("member")Member member){
        return "basic/login/addmember";
    }

    @ModelAttribute("member")
    @Validated // 추가
    public Member createEmptyMember() {
        return new Member();
    }

    @PostMapping("/checkid")
    @ResponseBody
    public int checkId(@RequestBody String username) {
        return memberService.findByIdBoolean(username);
    }

    @PostMapping("/join")
    public String Join(@Validated @ModelAttribute("member") Member member, BindingResult memberBindingResult, Model model){
        if(memberBindingResult.hasErrors()){
            return "basic/login/addmember";
        }
        member.setOrgpassword(member.getPassword());
        member.setPassword(passwordEncoder.encode(member.getOrgpassword()));
        memberService.memberSave(member);
        return "redirect:/yw/login";
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error",required = false)String error,
                            @RequestParam(value = "exception",required = false)String exception,
                            Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "basic/login/loginform";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/yw/login";
    }
}
