package com.wsystem.ImagesGallery.repository;

import com.wsystem.ImagesGallery.domain.entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Images, String> {

}
