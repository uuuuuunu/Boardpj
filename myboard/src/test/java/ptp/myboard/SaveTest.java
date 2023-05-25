package ptp.myboard;

import org.junit.jupiter.api.Test;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Member;
import ptp.myboard.repository.BoardRepository;
import ptp.myboard.repository.MemberReposirory;
import ptp.myboard.service.BoardService;
import ptp.myboard.service.MemberService;

import java.util.UUID;

public class SaveTest {
    BoardService boardService;
    BoardRepository boardRepository;
    MemberService memberService;
    @Test
    public void Savetest(){
        Board board=new Board();

        board.setId(1L);
        board.setSts(String.valueOf(1));
        board.setHit(1);
        board.setPrice(10000);
        board.setTitle("1");
        board.setCont("2");
        //board.setCreatAt(1);
        Member member=new Member();
        member.setPhnum("1");
        member.setName("1");
        member.setUsername("1");
        member.setPassword("1");
        board.setMember(member);
        memberService.memberSave(member);
        boardService.boardSave(board);

    }

    @Test
    public void MemberTest(){
        Member member=new Member();
        member.setPhnum("1");
        member.setName("1");
        member.setUsername("1");
        member.setPassword("1");
        //board.setMember(member);
        memberService.memberSave(member);

    }
    @Test
    public void uuidtest(){
        String file="_hi_hello_ii".replaceAll("_","");
        System.out.println(file);

    }
}
