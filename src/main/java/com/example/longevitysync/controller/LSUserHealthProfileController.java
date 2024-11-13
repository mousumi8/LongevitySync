package com.example.longevitysync.controller;

import com.example.longevitysync.model.LSUserHealthProfile;
import com.example.longevitysync.repository.LSUserHealthProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-health-profiles")
public class LSUserHealthProfileController {

    @Autowired
    private LSUserHealthProfileRepository userHealthProfileRepository;

    // Get all user health profiles
    @GetMapping
    public ResponseEntity<List<LSUserHealthProfile>> getAllUserHealthProfiles() {
        List<LSUserHealthProfile> profiles = userHealthProfileRepository.findAll();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    // Get a specific user health profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<LSUserHealthProfile> getUserHealthProfileById(@PathVariable("id") Long id) {
        Optional<LSUserHealthProfile> profile = userHealthProfileRepository.findById(id);
        return profile.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new user health profile
    @PostMapping
    public ResponseEntity<LSUserHealthProfile> createUserHealthProfile(@RequestBody LSUserHealthProfile userHealthProfile) {
        try {
            LSUserHealthProfile savedProfile = userHealthProfileRepository.save(userHealthProfile);
            return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing user health profile
    @PutMapping("/{id}")
    public ResponseEntity<LSUserHealthProfile> updateUserHealthProfile(
            @PathVariable("id") Long id, @RequestBody LSUserHealthProfile updatedProfile) {

        Optional<LSUserHealthProfile> existingProfile = userHealthProfileRepository.findById(id);
        if (existingProfile.isPresent()) {
            LSUserHealthProfile profile = existingProfile.get();
            // Update fields from the request
            profile.setBodyType(updatedProfile.getBodyType());
            profile.setFoodHabit(updatedProfile.getFoodHabit());
            profile.setPhysicalActivity(updatedProfile.getPhysicalActivity());
            profile.setAllergyIds(updatedProfile.getAllergyIds());
            
            LSUserHealthProfile savedProfile = userHealthProfileRepository.save(profile);
            return new ResponseEntity<>(savedProfile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a user health profile by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserHealthProfile(@PathVariable("id") Long id) {
        try {
            if (userHealthProfileRepository.existsById(id)) {
                userHealthProfileRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
