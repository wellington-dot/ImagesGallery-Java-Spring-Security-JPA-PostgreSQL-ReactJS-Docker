package com.wsystem.ImagesGallery.application.Images;

import com.wsystem.ImagesGallery.domain.entities.Images;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/images")
public class ImagesController {

    private final ImagesServiceImpl service;
    private final ImagesMapper imgMapper;

    @PostMapping
    public ResponseEntity saveImage(  // -> ResponseEntity - repsonsável por retornar uma responsta HTTP, 200, 404, 201......
            @RequestParam("file") MultipartFile file, // Recebe a imagem no tipo MultipartFile, ñ Json
            @RequestParam("name") String name, // o nome da imagem
            @RequestParam("tags")List<String> tags // lista de tag para busca futura
            ) throws IOException {

        log.info("Imagem recebida, name: {}, size: {}", file.getContentType(), file.getSize());

        Images img = imgMapper.mapToImage(file, name, tags);
        Images savedImage = service.save(img); // recebe o objeto com ID salvo no banco
        URI imageUri = buildImageURL(savedImage); // chama metodo para criar URl

        return ResponseEntity.created(imageUri).build();
    }

    public URI buildImageURL(Images img){
        String imagePath = "/" + img.getId(); // conf. o / id da imagem salvo no banco, para compor URL
        return ServletUriComponentsBuilder
                .fromCurrentRequest() // current request é a rota principal "http://localhost:8080/v1/images"
                .path(imagePath)      // aqui adiciona o id no final, como referencia a foto "/soifjois74928"
                .build().toUri();
    }
}
