package ptp.myboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Reply;
import ptp.myboard.repository.ReplyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {
    private final ReplyRepository replyRepository;

    public void saveReply(Reply reply){
        replyRepository.save(reply);
    }
    public List<Reply> findAllreply(Reply reply){
        List<Reply> replyList= replyRepository.findAll();
        return replyList;
    }
    public List<Reply> findreply(Reply reply,Long id){
        return findAllreply(reply).stream()
                .filter(i->i.getBoard().getId().equals(id)).collect(Collectors.toList());
    }
}
