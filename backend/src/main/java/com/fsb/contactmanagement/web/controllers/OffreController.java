package com.fsb.contactmanagement.web.controllers;

import com.fsb.contactmanagement.business.services.OffreService;
import com.fsb.contactmanagement.dao.entities.Offre;
import com.fsb.contactmanagement.web.dto.OffreSummaryDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
                offreService.getAllOffres(),
                HttpStatus.OK
        );
    }

    @GetMapping("rand")
    public ResponseEntity<?> getRandomOffres() {
        return new ResponseEntity<>(
                offreService.getRandomOffres(),
                HttpStatus.OK
        );
    }

    @GetMapping("categorie")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(offreService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOffreById(@PathVariable Long id) {
        return new ResponseEntity<>(offreService.getOffreById(id), HttpStatus.OK);
    }
    @GetMapping("file/{id}")
    public ResponseEntity<?> getImageOffreById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=\"file_" + id + ".jpg\"");

        return new ResponseEntity<>(offreService.getImageOffreById(id), headers, HttpStatus.OK);
    }
//    @GetMapping()
//    public ResponseEntity<?> getAllOffres() {
//        return new ResponseEntity<>(
//                offreService.getAllOffres()
//                        .stream()
//                        .map(OffreSummaryDTO::toOffreSummaryDTO)
//                        .collect(Collectors.toList()),
//                HttpStatus.OK
//        );
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOffreById(@PathVariable Long id) {
//        return new ResponseEntity<>(OffreSummaryDTO.toOffreSummaryDTO(offreService.getOffreById(id)), HttpStatus.OK);
//    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<?> getOffresByNom(@PathVariable String nom) {
        return new ResponseEntity<>(offreService.getAllOffresByNom(nom), HttpStatus.OK);
    }
    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<?> getOffresByCategorie(@PathVariable String categorie) {
        return new ResponseEntity<>(offreService.getAllOffresByCategorie(categorie), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addOffre(@RequestBody Offre offre) {
        return new ResponseEntity<>(offreService.addOffre(offre), HttpStatus.CREATED);
    }

    @PostMapping(path="file", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addOffreFile(@ModelAttribute OffreSummaryDTO offreS) {
        try {
            // Extract the file and other details
            MultipartFile image = offreS.getImage();
            String nom = offreS.getNom();
            String categorie = offreS.getCategorie();
            Integer duration = offreS.getDuration();
            Double remise = offreS.getRemise();
            String description = offreS.getDescription();
            Double prix = offreS.getPrix();

            // Create and populate the entity
            Offre offre = new Offre();
            offre.setNom(nom);
            offre.setDescription(description);
            offre.setPrix(prix);
            offre.setRemise(remise);
            offre.setDuration(duration);
            offre.setCategorie(categorie);
            offre.setImage(image.getBytes());

            offreService.addOffre(offre);

            return new ResponseEntity<>("File uploaded and saved successfully!", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "file/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateOffreFile(@PathVariable Long id, @ModelAttribute OffreSummaryDTO offreS) {
        try {
            // Fetch the existing offre by id
            Offre existingOffre = offreService.getOffreById(id); // Assuming you have a method to fetch Offre by ID

            if (existingOffre == null) {
                return new ResponseEntity<>("Offre not found", HttpStatus.NOT_FOUND);
            }

            // Extract the file and other details
            MultipartFile image = offreS.getImage();
            String nom = offreS.getNom();
            String categorie = offreS.getCategorie();
            Integer duration = offreS.getDuration();
            Double remise = (offreS.getRemise()==0)?null:offreS.getRemise();
            String description = offreS.getDescription();
            Double prix = offreS.getPrix();

            // Update the existing Offre with new values
            existingOffre.setNom(nom);
            existingOffre.setDescription(description);
            existingOffre.setPrix(prix);
            existingOffre.setRemise(remise);
            existingOffre.setDuration(duration);
            existingOffre.setCategorie(categorie);

            // If a new image is provided, update the image field
            if (image != null && !image.isEmpty()) {
                existingOffre.setImage(image.getBytes());
            }

            // Save the updated Offre
            offreService.updateOffre(existingOffre.getId(), existingOffre); // Assuming you have a method to update an existing Offre

            return new ResponseEntity<>("File updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOffre(@PathVariable Long id) {
        offreService.deleteOffreById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
