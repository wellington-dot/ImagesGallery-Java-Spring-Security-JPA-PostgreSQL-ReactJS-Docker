package com.wsystem.ImagesGallery.application.Images;

import com.wsystem.ImagesGallery.domain.entities.Images;
import com.wsystem.ImagesGallery.domain.enums.ImageExtension;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Component
public class ImagesMapper {

    public Images mapToImage (MultipartFile file, String name, List<String> tags) throws IOException {
        return Images.builder()
                .name(name)
                .size(file.getSize())
                .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .tags(String.join("," , tags ))
                .file(file.getBytes())
                .build();
    }
}

