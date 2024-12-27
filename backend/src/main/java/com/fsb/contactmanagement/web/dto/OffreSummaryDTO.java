package com.fsb.contactmanagement.web.dto;

import com.fsb.contactmanagement.dao.entities.Offre;

import java.util.List;

public record OffreSummaryDTO (
    Long id,
    String nom,
    String description,
    Double prix,
    Double remise,
    String categorie,
    Integer duration

) {
    public static OffreSummaryDTO toOffreSummaryDTO(Offre o) {
        return new OffreSummaryDTO(
                o.getId(),
                o.getNom(),
                o.getDescription(),
                o.getPrix(),
                o.getRemise(),
                o.getCategorie(),
                o.getDuration()
        );
    }
}
