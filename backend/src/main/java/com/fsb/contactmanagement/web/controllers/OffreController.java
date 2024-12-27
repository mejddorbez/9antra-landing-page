package com.fsb.contactmanagement.web.controllers;

import com.fsb.contactmanagement.business.services.OffreService;
import com.fsb.contactmanagement.dao.entities.Offre;
import com.fsb.contactmanagement.web.dto.OffreSummaryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/offres")
public class OffreController {
    private final OffreService offreService;

    public OffreController(OffreService offreService) {
        this.offreService = offreService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllOffres() {
        return new ResponseEntity<>(
                offreService.getAllOffres()
                        .stream()
                        .map(OffreSummaryDTO::toOffreSummaryDTO)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOffreById(@PathVariable Long id) {
        return new ResponseEntity<>(OffreSummaryDTO.toOffreSummaryDTO(offreService.getOffreById(id)), HttpStatus.OK);
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<?> getOffresByNom(@PathVariable String nom) {
        return new ResponseEntity<>(offreService.getAllOffresByNom(nom), HttpStatus.OK);
    }
    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<?> getOffresByCategorie(@PathVariable String categorie) {
        return new ResponseEntity<>(offreService.getAllOffresByNom(categorie), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addOffre(@RequestBody Offre offre) {
        return new ResponseEntity<>(offreService.addOffre(offre), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody Offre         offre) throws Exception {
        return new ResponseEntity<>(this.offreService.updateOffre(id, offre), HttpStatus.OK);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOffre(@PathVariable Long id) {
        offreService.deleteOffreById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
