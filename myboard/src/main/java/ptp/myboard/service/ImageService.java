package ptp.myboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ptp.myboard.domain.Image;
import ptp.myboard.repository.ImageRepository;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
    private final ImageRepository imageRepository;
    //String filepath="C:\\toy\\myboard\\src\\main\\resources\\static\\image\\";
    public Image pathsave(Image image){
        return imageRepository.save(image);
    }

    public Image findByimgid(Long id){
        return imageRepository.getReferenceById(id);
    }

    public void uploadImage(@NotNull MultipartFile imgfile, @NotNull Image image) throws IOException {
        String orgname=imgfile.getOriginalFilename();
        String property=System.getProperty("user.dir")+"\\myboard\\src\\main\\resources\\static\\image\\";
        String imgName=uuidname(orgname);
        File saveFile=new File(property,imgName);
        imgfile.transferTo(saveFile);
        image.setOrgImageName(imgName);
        image.setImagePath("/image/"+imgName);
        pathsave(image);

    }
    private String uuidname(String orgname){
        String uuid = UUID.randomUUID().toString();
        uuid=uuid.replace("-","_");
        return uuid+orgname;
    }
}
