package ptp.myboard.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Member;
import ptp.myboard.repository.BoardRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MypageService {



    private final MemberService memberService;
    private final BoardService boardService;
    private final Board board;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void editmember(String username, Member member) {
        Member findmember = memberService.findById(username);
        String rawPassword = member.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        member.setPassword(encPassword);
        findmember.setUsername(member.getUsername());
        findmember.setPassword(member.getPassword());
        findmember.setNickname(member.getNickname());
        findmember.setName(member.getName());
        findmember.setEmail(member.getEmail());

    }

        public List<Board> mypagepost(String username) {
            List<Board> allbd = boardService.findAllbd(board);
            Member byId = memberService.findById(username);
            List<Board> mypagepost = new ArrayList<>();
            List<Board> tolist=
                    allbd.stream().filter(i->i.getMember().getUsername().equals(byId.getUsername()))
                            .collect(Collectors.toList());
            return tolist;
        }

}
