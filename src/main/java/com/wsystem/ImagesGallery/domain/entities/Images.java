package com.wsystem.ImagesGallery.domain.entities;

import com.wsystem.ImagesGallery.domain.enums.ImageExtension;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data                   // Getters e Setters, Hash e equals, toString
@NoArgsConstructor      // Construtor sem argumentos
@AllArgsConstructor     // com argumentos
@Builder
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Gera um id tipo hash
    private String id;
    private String name;
    private Long size;

    @Enumerated(EnumType.STRING)  // salva o enum como string no BD
    private ImageExtension extension;

    @CreatedDate
    private LocalDateTime uploadDate; // cria data formato - (2025-12-30T14:35:22.123456)
    private String tags;

    @Lob    // usado para salvar arquivos binarios (imagens, pdfs...)
    private byte[] file;
}
