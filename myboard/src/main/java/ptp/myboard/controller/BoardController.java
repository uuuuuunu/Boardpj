package ptp.myboard.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Member;
import ptp.myboard.service.BoardService;
import ptp.myboard.service.ImageService;
import ptp.myboard.service.MemberService;
import ptp.myboard.domain.Image;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
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

    @GetMapping("/boards")
    public String boardList(Board board, Model model) {
        List<Board> boards = boardService.findAllbd(board);
        for (Board board1 : boards) {
            List<Image> image = board1.getImage();
        }
        model.addAttribute("boards", boards);
        return "basic/board/boards";
    }

    @GetMapping("/boards/new")
    public String newform(@ModelAttribute("board")Board board,
                          @ModelAttribute("image") Image image,
                          Model model,Principal principal){
        String username=principal.getName();
        Member member=memberService.findById(username);
        model.addAttribute("nick",member.getNickname());
        return "basic/board/addboard";
    }


    @PostMapping("/boards/new")
    public String newboard(@ModelAttribute("board")@Valid Board board , BindingResult bindingResult,
                           Principal principal,Model model,
                           @RequestPart List<MultipartFile> imgfile, Image image) throws IOException {
        if(bindingResult.hasErrors()){
            return "basic/board/addboard";
        }
        String username=principal.getName();
        Member member=memberService.findById(username);
        List<Image> imgs = imageService.findAllImg(image);
        board.setMember(member);
        board.setImage(imgs);
        image.setBoard(board);
        boardService.boardSave(board);
        imageService.uploadImage(imgfile,board);
        model.addAttribute("image",image);
        return "redirect:/yw/boards";
    }

    @GetMapping("/boards/{id}")
    public String board(@PathVariable Long id,
                        @NotNull Model model, @NotNull Principal principal) throws IOException {
        Board findbd=boardService.findById(id);
        boardService.Hit(id);
        String nickname=principal.getName();
        model.addAttribute("nickname",nickname);
        model.addAttribute("board",findbd);
        String cont=findbd.getCont().replace("\r\n","<br>");
        log.info("cont={}",cont);
        log.info("boardcont={}",findbd.getCont());
        model.addAttribute("cont",cont);
        return "basic/board/board";
    }
}

