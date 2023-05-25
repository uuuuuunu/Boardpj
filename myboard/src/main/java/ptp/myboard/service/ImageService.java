package ptp.myboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ptp.myboard.domain.Image;
import ptp.myboard.domain.Member;
import ptp.myboard.repository.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;

    public Image imgsave(Image image){
        return imageRepository.save(image);
    }
    public Image findByimgid(Long id){
        return imageRepository.getReferenceById(id);
    }
    public String findByorgname(Long boardid, Image image){
        if(image.getBoard().getId()==boardid){
            return image.getOrgImageName();
        }else{
            return null;
        }
    }
    public void imagePathSave(@NotNull MultipartFile file, Image image) throws IOException {
        String filepath="C:\\toy\\myboard\\src\\main\\resources\\static\\image\\";
        String uuid = UUID.randomUUID().toString();
        if(!file.isEmpty()){
            String orgname=file.getOriginalFilename();
            String savename=uuid+orgname;
            log.info("orgname={}",orgname);
            image.setImagePath(filepath+orgname);
            image.setOrgImageName(orgname);
            image.setSaveName(savename);
            imgsave(image);
            File save=new File(filepath+savename);
            file.transferTo(save);
        }

    }

}
