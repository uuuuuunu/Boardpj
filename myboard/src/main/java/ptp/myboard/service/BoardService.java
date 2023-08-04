package ptp.myboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Image;
import ptp.myboard.domain.Member;
import ptp.myboard.repository.BoardRepository;
import ptp.myboard.repository.ImageRepository;

import javax.sound.sampled.FloatControl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {


    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    public Board boardSave(Board board){
        return boardRepository.save(board);
    }

    public List<Board> findAllbd(){
        List<Board> boards= boardRepository.findAll();
        return boards;
    }

    public List<Board> searchBoard(String keyword){
        List<Board> searchboards=new ArrayList<>();
        List<Board> boards=findAllbd();
        for (Board board1 : boards) {
            if(board1.getTitle().contains(keyword)){
                searchboards.add(board1);
            }
        }
        return searchboards;

    }

    public Board findById(Long Id){
        return boardRepository.findById(Id).orElse(null);
    }


    public void Delete(long id){
        //imageRepository.deleteById(id);
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, @NotNull Board board,List<MultipartFile> imgfile) throws IOException {
        Board findboard=findById(id);
        findboard.setCont(board.getCont());
        findboard.setTitle(board.getTitle());
        findboard.setSts(board.getSts());
        findboard.setPrice(board.getPrice());
        //log.info("imgfile2={}",imgfile.isEmpty());
        //log.info("imgfile3={}",imgfile.size());
        log.info("imgfilelength={}",imgfile.get(0).getOriginalFilename().length());
        if(imgfile.get(0).getOriginalFilename().length()>1){
            imageService.updateImage(findboard,imgfile);
        }
    }

    public Board Hit(Long Id){
        Board byId = findById(Id);
        byId.hit++;
        return boardRepository.save(byId);
    }
}
