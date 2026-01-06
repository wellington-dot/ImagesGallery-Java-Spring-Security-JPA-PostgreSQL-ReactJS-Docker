package com.wsystem.ImagesGallery.infra.repository;

import com.wsystem.ImagesGallery.domain.entities.Image;
import com.wsystem.ImagesGallery.domain.enums.ImageExtension;
import com.wsystem.ImagesGallery.infra.repository.specs.GenericSpecs;
import com.wsystem.ImagesGallery.infra.repository.specs.ImageSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {

    // Montando esse SQL utilizando o Specification
    // SELECT * FROM IMAGE WHERE 1 = 1 AND EXTENSION = 'PNG' AND (NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY')
    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query){

        // SELECT * FROM IMAGE WHERE 1 = 1
        Specification<Image> spec = Specification.where(GenericSpecs.conjunction());

        // AND EXTENSION = 'PNG'
        if (extension != null){
            spec = spec.and(ImageSpecs.extensionEqual(extension));
        }

        // AND (NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY')]
        if (StringUtils.hasText(query)){ // StringUtils.hasText válida até se enviar um " " espaco, ou string vazia, ou não for null
            spec = spec.and(Specification.anyOf(ImageSpecs.nameLike(query), ImageSpecs.tagsLike(query)));
        }

        return findAll(spec);
    }

}


















