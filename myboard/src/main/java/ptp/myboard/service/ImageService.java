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
    public void imagePathSave(@NotNull MultipartFile file, Image image) throws IOException {
        String filepath="C:\\toy\\myboard\\src\\main\\resources\\static\\image";
        UUID uuid=UUID.randomUUID();
        if(!file.isEmpty()){
            String orgname=uuid+file.getOriginalFilename();
            image.setImagePath(filepath+orgname);
            image.setOrgImageName(orgname);
            imageRepository.save(image);
            File save=new File(filepath,image.getOrgImageName());
            file.transferTo(save);
        }
    }
}
