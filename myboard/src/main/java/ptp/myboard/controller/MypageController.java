package ptp.myboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.exceptions.TemplateInputException;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Image;
import ptp.myboard.domain.Member;
import ptp.myboard.domain.Reply;
import ptp.myboard.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/yw")
public class MypageController {
    private final MemberService memberService;
    private final MypageService mypageService;
    private final BoardService boardService;
    private final ReplyService replyService;

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
        return "redirect:/yw/boards/{username}/myedit";
    }

    @GetMapping("boards/{username}/mypost")
    public String mypostform(@PathVariable String username, Model model){
        List<Board> mypagepost = mypageService.mypagepost(username);
        model.addAttribute("myposts",mypagepost);
        return "basic/mypage/myboards";
    }
    @GetMapping("boards/{username}/myreply")
    public String myreplyform(@PathVariable String username,Model model,Reply reply){
        List<Reply> userReply=replyService.findUserReply(reply, username);
        model.addAttribute("userrep",userReply);
        return "basic/mypage/myreplyboards";
    }
    @PostMapping("boards/{username}/myreply")
    public String deletereply(@PathVariable String username,Reply reply,
                              @RequestParam List<Long> chkrpy){
        try {
            List<Reply> userReply=replyService.findUserReply(reply, username);
            for (Reply reply1 : userReply) {
                for (Long chkrp : chkrpy) {
                    log.info("chkrp={}",chkrp);
                    if(reply1.getRepid().equals(chkrp)){
                        replyService.deleteReply(reply1);
                    }
                }
            }
        }catch (TemplateInputException e){
            return null;
        }
        return "redirect:/yw/boards/{username}/myreply";
    }

    @GetMapping("/boards/mypage/{id}")
    public String board(@PathVariable Long id, Model model, Principal principal){
        Board findb=boardService.findById(id);
        String nickname=principal.getName();
        model.addAttribute("board",findb);
        model.addAttribute("nickname",nickname);
        //Image image = imageService.findByimgid(id);
        List<Image> image = findb.getImage();
        for (Image image1 : image) {
            model.addAttribute("image",image1);
        }
        return "basic/mypage/myboard";
    }

    @GetMapping("/boards/{id}/edit")
    public String editform(@PathVariable Long id, Model model,Principal principal){
        Board findbd=boardService.findById(id);
        String nickname=principal.getName();
        model.addAttribute("board",findbd);
        model.addAttribute("nickname",nickname);
        return "basic/mypage/myboardedit";
    }


    @PostMapping ("/boards/{id}/edit")
    public String editbd(@PathVariable Long id, @ModelAttribute("board") @Valid Board board,
                         BindingResult bindingResult, @RequestPart List<MultipartFile> imgfile, Image image,
                         HttpServletRequest request)
            throws IOException {
        if(bindingResult.hasErrors()){
            return "basic/mypage/myboardedit";
        }
        boardService.update(id, board, imgfile);
        return "redirect:/yw/boards/{id}";


    }
    @GetMapping  ("/boards/{id}/delete")
    public String delete(@PathVariable Long id, Board board,Model model){
        boardService.Delete(id);
        model.addAttribute("board",board);
        return "redirect:/yw/boards";
    }

}

