package com.wsystem.ImagesGallery.application.Images;

import com.wsystem.ImagesGallery.domain.entities.Image;
import com.wsystem.ImagesGallery.domain.enums.ImageExtension;
import com.wsystem.ImagesGallery.domain.service.ImageService;
import com.wsystem.ImagesGallery.infra.repository.ImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImagesServiceImpl implements ImageService {

    private final ImagesRepository repository;

    @Override
    @Transactional  // para criar uma transação no BD, só é necessário se for de escrita
    public Image save(Image img) {
        return repository.save(img);
    }

    @Override
    public Optional<Image> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Image> search(ImageExtension extension, String query) {
        return repository.findByExtensionAndNameOrTagsLike(extension, query);
    }
}
