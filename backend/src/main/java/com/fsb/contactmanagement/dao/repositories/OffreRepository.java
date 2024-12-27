package com.fsb.contactmanagement.dao.repositories;

import com.fsb.contactmanagement.dao.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {

    List<Offre> findOffresByNomStartingWith(String name);
    List<Offre> findOffresByCategorie(String categorie);
}
