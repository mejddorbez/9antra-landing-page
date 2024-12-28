package com.fsb.contactmanagement.web.dto;

import com.fsb.contactmanagement.dao.entities.Offre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OffreSummaryDTO implements Serializable {
    private String nom;
    private String description;
    private Double prix;
    private Double remise;
    private String categorie;
    private Integer duration;
    private MultipartFile image;
}
