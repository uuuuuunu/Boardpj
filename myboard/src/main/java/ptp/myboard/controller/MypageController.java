package ptp.myboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Member;
import ptp.myboard.service.BoardService;
import ptp.myboard.service.MemberService;
import ptp.myboard.service.MypageService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/yw")
public class MypageController {
    private final MemberService memberService;
    private final MypageService mypageService;
    private final BoardService boardService;
    private final Board board;

    @ModelAttribute
    public void nicknameinform(Principal principal, Model model){
        Member byId = memberService.findById(principal.getName());
        if(principal!=null) {
            model.addAttribute("mesg", "반갑습니다." + byId.getNickname() + "님");
        }
    }
    @ModelAttribute
    public void nickname(Principal principal,Model model){
        Member byId = memberService.findById(principal.getName());
        model.addAttribute("nick",byId.getNickname());
    }

    @GetMapping("/boards/myhome")
    public String mypagehome(Model model,Principal principal){
        Member byId = memberService.findById(principal.getName());
        model.addAttribute("mypage",byId);
        return "basic/mypage/myhome";
    }

    @GetMapping("boards/{username}/myedit")
    public String myeditform(@PathVariable String username, Model model,Principal principal){
        Member byId = memberService.findById(principal.getName());
        model.addAttribute("member",byId);
        return "basic/mypage/myedit";
    }
    @PostMapping("boards/{username}/myedit")
    public String myedit(@ModelAttribute("member") @Valid Member member, BindingResult bindingResult,
            @PathVariable String username){
        if(bindingResult.hasErrors()){
            return "basic/mypage/myedit";
        }
        mypageService.editmember(username,member);
        return "redirect:/yw/boards/myhome";
    }

    @GetMapping("boards/{username}/mypost")
    public String mypostform(@PathVariable String username, Model model){
        List<Board> mypagepost = mypageService.mypagepost(username);
        model.addAttribute("myposts",mypagepost);
        return "basic/mypage/myboards";
    }

    @GetMapping("/boards/mypage/{id}")
    public String board(@PathVariable Long id, Model model, Principal principal){
        Board findb=boardService.findById(id);
        String nickname=principal.getName();
        model.addAttribute("board",findb);
        model.addAttribute("nickname",nickname);
        return "basic/mypage/myboard";
    }

    @GetMapping("/boards/{id}/edit")
    public String editform(@PathVariable Long id, Model model,Principal principal){
        Board findbd=boardService.findById(id);
        String nickname=principal.getName();
        model.addAttribute("board",findbd);
        model.addAttribute("nickname",nickname);
        return "basic/mypage/editform";
    }

    @PostMapping ("/boards/{id}/edit")
    public String editbd(@PathVariable Long id, @ModelAttribute("board") @Valid Board board,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "basic/mypage/editform";
        }
        boardService.update(id,board);
        return "redirect:/yw/boards/{id}";
    }
    @GetMapping  ("/boards/{id}/delete")
    public String delete(@PathVariable Long id, Board board,Model model){
        boardService.Delete(id);
        model.addAttribute("board",board);
        return "redirect:/yw/boards";
    }


}

