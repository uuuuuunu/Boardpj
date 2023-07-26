package ptp.myboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ptp.myboard.domain.Board;
import ptp.myboard.domain.Image;
import ptp.myboard.repository.ImageRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;
    String property=System.getProperty("user.dir")+"\\myboard\\src\\main\\resources\\static\\image\\";

    public Image pathsave(Image image){
        return imageRepository.save(image);
    }

    public Image findByimgid(Long id){
        return imageRepository.getReferenceById(id);
    }

    public List<Image> findAllImg(Image image){
        List<Image> imgs=imageRepository.findAll();
        return imgs;
    }

    public void uploadImage(List<MultipartFile> imgfile,Board board) throws IOException {
        for (MultipartFile files : imgfile) {
            if(!files.isEmpty()){
                Image image=new Image();
                String orgname=files.getOriginalFilename();
                String imgName=uuidname(orgname);
                //log.info("orgname={}",files.getOriginalFilename());
                //log.info("imgname={}",imgName);
                File saveFile=new File(property,imgName);
                files.transferTo(saveFile);
                image.setOrgImageName(imgName);
                image.setImagePath("/image/"+imgName);
                image.setBoard(board);
                //log.info("imagepath={}",image.getImagePath());
                pathsave(image);
            }
        }
    }
    public void updateImage(Board board,List<MultipartFile> imgfile) throws IOException {
        List<Image> images = board.getImage();
        //log.info("imgsize={}",imgfile.size());
        for(Image image:images){
            File f=new File(property,image.getOrgImageName());
            f.delete();
            imageRepository.delete(image);
        }
        uploadImage(imgfile,board);
    }

    private String uuidname(String orgname){
        String uuid = UUID.randomUUID().toString();
        uuid=uuid.replace("-","_");
        return uuid+orgname;
    }

}
