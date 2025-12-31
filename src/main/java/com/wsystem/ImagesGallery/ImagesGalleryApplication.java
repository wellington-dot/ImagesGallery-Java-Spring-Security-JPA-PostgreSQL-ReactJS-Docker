package com.wsystem.ImagesGallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImagesGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagesGalleryApplication.class, args);
	}

}
