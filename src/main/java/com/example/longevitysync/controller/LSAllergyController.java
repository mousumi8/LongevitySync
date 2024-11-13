package com.example.longevitysync.controller;

import com.example.longevitysync.model.LSAllergy;
import com.example.longevitysync.repository.LSAllergyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/allergies")
public class LSAllergyController {

    @Autowired
    private LSAllergyRepository allergyRepository;

    // Get all allergies
    @GetMapping
    public ResponseEntity<List<LSAllergy>> getAllAllergies() {
        List<LSAllergy> allergies = allergyRepository.findAll();
        return new ResponseEntity<>(allergies, HttpStatus.OK);
    }

    // Get a specific allergy by ID
    @GetMapping("/{id}")
    public ResponseEntity<LSAllergy> getAllergyById(@PathVariable("id") Long id) {
        Optional<LSAllergy> allergy = allergyRepository.findById(id);
        return allergy.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new allergy
    @PostMapping
    public ResponseEntity<LSAllergy> createAllergy(@RequestBody LSAllergy allergy) {
        try {
            LSAllergy savedAllergy = allergyRepository.save(allergy);
            return new ResponseEntity<>(savedAllergy, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing allergy
    @PutMapping("/{id}")
    public ResponseEntity<LSAllergy> updateAllergy(@PathVariable("id") Long id, @RequestBody LSAllergy updatedAllergy) {
        Optional<LSAllergy> existingAllergy = allergyRepository.findById(id);
        if (existingAllergy.isPresent()) {
            LSAllergy allergy = existingAllergy.get();
            allergy.setAllergyName(updatedAllergy.getAllergyName());
            // allergy.setDescription(updatedAllergy.getDescription());

            LSAllergy savedAllergy = allergyRepository.save(allergy);
            return new ResponseEntity<>(savedAllergy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an allergy by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAllergy(@PathVariable("id") Long id) {
        try {
            if (allergyRepository.existsById(id)) {
                allergyRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
