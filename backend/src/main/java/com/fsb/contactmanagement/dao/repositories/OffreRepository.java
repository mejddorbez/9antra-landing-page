package com.fsb.contactmanagement.dao.repositories;

import com.fsb.contactmanagement.dao.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {

    List<Offre> findOffresByNomStartingWith(String name);
    List<Offre> findOffresByCategorie(String categorie);
    @Query(value = "SELECT * FROM offre ORDER BY RAND() LIMIT 2", nativeQuery = true)
    List<Offre> findRandomOffer();
    @Query("SELECT DISTINCT o.categorie FROM Offre o")
    List<String> findAllCategories();
    Offre findOffreById(Long Id);
}

