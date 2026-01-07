package com.wsystem.ImagesGallery.application.Images;

import com.wsystem.ImagesGallery.domain.entities.Image;
import com.wsystem.ImagesGallery.domain.enums.ImageExtension;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/images")
public class ImagesController {

    private final ImagesServiceImpl service;
    private final ImagesMapper imgMapper;

    @PostMapping
    public ResponseEntity saveImage(  // -> ResponseEntity - responsável por retornar uma responsta HTTP, 200, 404, 201......
            @RequestParam("file") MultipartFile file, // Recebe a imagem no tipo MultipartFile, ñ Json
            @RequestParam("name") String name, // o nome da imagem
            @RequestParam("tags")List<String> tags // lista de tag para busca futura
            ) throws IOException {

        log.info("Imagem recebida, name: {}, size: {}", file.getContentType(), file.getSize());

        Image img = imgMapper.mapToImage(file, name, tags);
        Image savedImage = service.save(img); // recebe o objeto com ID salvo no banco
        URI imageUri = buildImageURL(savedImage); // chama metodo para criar URl

        return ResponseEntity.created(imageUri).build();
    }

    public URI buildImageURL(Image img){
        String imagePath = "/" + img.getId(); // conf. o / id da imagem salvo no banco, para compor URL
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri() // current request é a rota principal "http://localhost:8080/v1/images"
                .path(imagePath)      // aqui adiciona o id no final, como referencia a foto "/soifjois74928"
                .build().toUri();
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id){
        var possibleImage = service.getById(id);
        // se não tiver imagem - retorna 404
        if(possibleImage.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var image = possibleImage.get(); // pega a imagem dentro o Object
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtension().getMediaType()); // pega a extensão da imagem
        headers.setContentLength(image.getSize()); // pega o tamanho da imagem
        headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() + "\" ", image.getFileName()); // monta o nome com a extensao

        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> search (
            @RequestParam(value = "extension", required = false, defaultValue = "") String extension,  // RequestParam pega os parametros query param, required false - não obrigatorio
            @RequestParam(value = "query", required = false) String query){

        var result = service.search(ImageExtension.ofName(extension), query);

        var images = result.stream().map(image -> {
            var url = buildImageURL(image);
            return imgMapper.imageToDTO(image, url.toString());
        }).collect(Collectors.toList());
        System.out.println("retorno bd: " + images.toString());
        return ResponseEntity.ok(images);
    }

}
