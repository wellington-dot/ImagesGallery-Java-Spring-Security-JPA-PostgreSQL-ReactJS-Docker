package com.wsystem.ImagesGallery.domain.enums;

import lombok.Getter;
import org.springframework.http.MediaType;
import java.util.Arrays;

public enum ImageExtension {

    JPEG(MediaType.IMAGE_JPEG),
    PNG(MediaType.IMAGE_PNG),
    GIF(MediaType.IMAGE_GIF);

    @Getter
    private MediaType mediaType;

    ImageExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    // metodo para comparação de extensão
    public static ImageExtension valueOf(MediaType mediaType){
        return Arrays.stream(values())
                .filter(imageExtension -> imageExtension.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }

    public static ImageExtension ofName(String name){
        return Arrays.stream(values())
                .filter(imageExtension -> imageExtension.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}

// Enum do tipo das imagens