package ptp.myboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Image;
import ptp.myboard.domain.Member;
import ptp.myboard.service.BoardService;
import ptp.myboard.service.ImageService;
import ptp.myboard.service.MemberService;

import javax.swing.filechooser.FileSystemView;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/yw")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;
    private final ImageService imageService;

    @ModelAttribute
    public void informnickname(Principal principal, Model model){
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

    @GetMapping("/boards")
    public String boardList(Board board, Model model,Principal principal) {
        List<Board> boards = boardService.findAllbd(board);
        Member byId = memberService.findById(principal.getName());
        model.addAttribute("boards", boards);
        model.addAttribute("nickname",byId.getNickname());
        return "basic/board/boards";
    }
    @GetMapping("/boards/new")
    public String newform(@ModelAttribute("board")Board board,
                          @ModelAttribute("image")Image image,Principal principal){
        return "basic/board/addboard";
    }

    @PostMapping("/boards/new")
    public String newboard(@ModelAttribute("board")@Valid Board board , BindingResult bindingResult,
                           Principal principal,Model model,
                           @RequestParam MultipartFile file, Image image) throws IOException {
        if(bindingResult.hasErrors()){
            return "basic/board/addboard";
        }
        String username=principal.getName();
        Member member=memberService.findById(username);
        board.setMember(member);
        boardService.boardSave(board);
        imageService.imagePathSave(file,image);
        model.addAttribute("image",image);
        return "redirect:/yw/boards";
    }


    @GetMapping("/boards/{id}")
    public String board(@PathVariable Long id, Model model,Principal principal){
        Board findbd=boardService.findById(id);
        boardService.Hit(id, findbd);
        String nickname=principal.getName();
        model.addAttribute("nickname",nickname);
        model.addAttribute("board",findbd);

        return "basic/board/board";
    }
}

