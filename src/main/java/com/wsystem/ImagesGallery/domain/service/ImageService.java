package com.wsystem.ImagesGallery.domain.service;

import com.wsystem.ImagesGallery.domain.entities.Image;
import com.wsystem.ImagesGallery.domain.enums.ImageExtension;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    Image save(Image img);

    Optional<Image> getById(String id);

    List<Image> search(ImageExtension extension, String query);
}
