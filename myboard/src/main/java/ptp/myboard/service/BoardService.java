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

    public List<Board> findAllbd(Board board){
        List<Board> boards= boardRepository.findAll();
        return boards;
    }


    public Board findById(Long Id){
        return boardRepository.findById(Id).orElse(null);
    }


    public void Delete(long id){
        imageRepository.deleteById(id);
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, @NotNull Board board,MultipartFile imgfile,Image image) throws IOException {
        Board findboard=findById(id);
        findboard.setCont(board.getCont());
        findboard.setTitle(board.getTitle());
        findboard.setSts(board.getSts());
        findboard.setPrice(board.getPrice());
        imageService.updateImage(findboard, imgfile);
    }

    public Board Hit(Long Id){
        Board byId = findById(Id);
        byId.hit++;
        return boardRepository.save(byId);
    }
}
