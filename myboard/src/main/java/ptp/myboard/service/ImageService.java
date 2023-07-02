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

    public void uploadImage(MultipartFile imgfile, Image image) throws IOException {
        String orgname=imgfile.getOriginalFilename();
        String imgName=uuidname(orgname);
        //log.info("orgname={}",imgfile.getOriginalFilename());
        //log.info("imgname={}",imgName);
        File saveFile=new File(property,imgName);
        imgfile.transferTo(saveFile);
        image.setOrgImageName(imgName);
        image.setImagePath("/image/"+imgName);
        //log.info("imagepath={}",image.getImagePath());
        pathsave(image);
    }
    public void updateImage(Board board,MultipartFile imgfile) throws IOException{
        List<Image> images = board.getImage();
        Image img=new Image();
        if(imgfile.getOriginalFilename()!=null) {
            for (Image image : images) {
                Long imageId = image.getImageId();
                File f=new File(property,image.getOrgImageName());
                f.delete();
                imageRepository.deleteById(imageId);
                img.setBoard(image.getBoard());
            }
            uploadImage(imgfile, img);
        }
    }

    private String uuidname(String orgname){
        String uuid = UUID.randomUUID().toString();
        uuid=uuid.replace("-","_");
        return uuid+orgname;
    }

}
