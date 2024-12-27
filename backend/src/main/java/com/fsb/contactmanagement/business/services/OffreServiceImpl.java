package com.fsb.contactmanagement.business.services;

import com.fsb.contactmanagement.dao.entities.Offre;
import com.fsb.contactmanagement.dao.repositories.OffreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OffreServiceImpl implements OffreService {

    private final OffreRepository offreRepository;

    public OffreServiceImpl(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }

    @Override
    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }
    @Override
    public List<Offre> getAllOffresByCategorie(String categorie) {
        return offreRepository.findOffresByCategorie(categorie);
    }

    @Override
    public List<Offre> getAllOffresByNom(String name) {
        return offreRepository.findOffresByNomStartingWith(name);
    }


    @Override
    public Offre getOffreById(Long Id) {
        return Id==null?null:offreRepository.findById(Id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Offre addOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public Offre updateOffre(Long id, Offre updatedOffre)throws Exception{
        Offre existingOffre;
        if (offreRepository.existsById(id)) {
            existingOffre = offreRepository.findById(id).get();
            existingOffre.setNom(updatedOffre.getNom());
            existingOffre.setDescription(updatedOffre.getDescription());
            existingOffre.setPrix(updatedOffre.getPrix());
            existingOffre.setCategorie(updatedOffre.getCategorie());
            existingOffre.setRemise(updatedOffre.getRemise());
            existingOffre.setDuration(updatedOffre.getDuration());
            return offreRepository.save(existingOffre);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteOffreById(Long id) {
        offreRepository.deleteById(id);
    }
}
