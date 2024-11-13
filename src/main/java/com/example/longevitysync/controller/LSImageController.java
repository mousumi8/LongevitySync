package com.example.longevitysync.controller;

import com.example.longevitysync.model.LSImage;
import com.example.longevitysync.repository.LSImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class LSImageController {

    @Autowired
    private LSImageRepository imageRepository;

    // Get all images
    @GetMapping
    public ResponseEntity<List<LSImage>> getAllImages() {
        List<LSImage> images = imageRepository.findAll();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    // Get a specific image by ID
    @GetMapping("/{id}")
    public ResponseEntity<LSImage> getImageById(@PathVariable("id") Long id) {
        Optional<LSImage> image = imageRepository.findById(id);
        return image.map(ResponseEntity::ok)
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new image
    @PostMapping
    public ResponseEntity<LSImage> createImage(@RequestBody LSImage image) {
        try {
            LSImage savedImage = imageRepository.save(image);
            return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing image
    @PutMapping("/{id}")
    public ResponseEntity<LSImage> updateImage(@PathVariable("id") Long id, @RequestBody LSImage updatedImage) {
        Optional<LSImage> existingImage = imageRepository.findById(id);
        if (existingImage.isPresent()) {
            LSImage image = existingImage.get();
            image.setImageType(updatedImage.getImageType());
            image.setImageLocation(updatedImage.getImageLocation());

            LSImage savedImage = imageRepository.save(image);
            return new ResponseEntity<>(savedImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an image by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteImage(@PathVariable("id") Long id) {
        try {
            if (imageRepository.existsById(id)) {
                imageRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
