package com.wsystem.ImagesGallery.application.Images;

import com.wsystem.ImagesGallery.domain.entities.Image;
import com.wsystem.ImagesGallery.domain.enums.ImageExtension;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Component
public class ImagesMapper {

    // Criando um obj do tipo Image, para salvar a imagem no BD
    public Image mapToImage (MultipartFile file, String name, List<String> tags) throws IOException {
        return Image.builder()
                .name(name)
                .size(file.getSize())
                .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .tags(String.join("," , tags ))
                .file(file.getBytes())
                .build();
    }

    // Criando um obj tipo ImageDTO - Transferencia de arquivos, e apresentação
    public ImageDTO imageToDTO(Image image, String url){
        return ImageDTO.builder()
                .url(url)
                .extension(image.getExtension().name())
                .name(image.getName())
                .size(image.getSize())
                .uploadDate(image.getUploadDate().toLocalDate())
                .build();
    }
}

