package ptp.myboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Member;
import ptp.myboard.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {


    private final BoardRepository boardRepository;
    private final MemberService memberService;



    public List<Board> findAllbd(Board board){
        List<Board> boards= boardRepository.findAll();
        return boards;
    }


    public Board findById(Long Id){
        return boardRepository.findById(Id).orElse(null);
    }


    public void Delete(long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id,Board board){
        Board findboard=findById(id);
        findboard.setName(board.getName());
        findboard.setCont(board.getCont());
        findboard.setTitle(board.getTitle());
        findboard.setSts(board.getSts());
        findboard.setPrice(board.getPrice());

    }
    public Board Hit(Long Id, Board board){
        Board byId = findById(Id);
        byId.hit++;
        return boardRepository.save(byId);

    }
}
