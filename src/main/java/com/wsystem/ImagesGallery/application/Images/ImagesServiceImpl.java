package com.wsystem.ImagesGallery.application.Images;

import com.wsystem.ImagesGallery.domain.entities.Images;
import com.wsystem.ImagesGallery.domain.service.ImageService;
import com.wsystem.ImagesGallery.repository.ImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImagesServiceImpl implements ImageService {

    private final ImagesRepository repository;

    @Override
    @Transactional    // para criar uma transação no BD
    public Images save(Images img) {
        return repository.save(img);
    }
}
