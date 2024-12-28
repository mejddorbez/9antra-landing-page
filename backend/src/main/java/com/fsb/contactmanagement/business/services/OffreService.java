package com.fsb.contactmanagement.business.services;

import com.fsb.contactmanagement.dao.entities.Offre;
import com.fsb.contactmanagement.web.dto.OffreSummaryDTO;

import java.util.List;

public interface OffreService {
    public List<Offre> getAllOffres();
    public List<String> getAllCategories();
    public List<Offre> getRandomOffres();
    public List<Offre> getAllOffresByNom(String nom);
    public List<Offre> getAllOffresByCategorie(String categorie);
    public Offre getOffreById(Long Id);
    public byte[] getImageOffreById(Long Id);
    public Offre addOffre(Offre offre);
    public Offre updateOffre(Long id, Offre offre) throws Exception;
    public void deleteOffreById(Long id);
}
