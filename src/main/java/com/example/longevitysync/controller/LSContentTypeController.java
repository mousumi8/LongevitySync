package com.example.longevitysync.controller;

import com.example.longevitysync.model.LSContentType;
import com.example.longevitysync.repository.LSContentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content-types")
public class LSContentTypeController {

    @Autowired
    private LSContentTypeRepository contentTypeRepository;

    // Get all content types
    @GetMapping
    public ResponseEntity<List<LSContentType>> getAllContentTypes() {
        List<LSContentType> contentTypes = contentTypeRepository.findAll();
        return new ResponseEntity<>(contentTypes, HttpStatus.OK);
    }

    // Get a specific content type by ID
    @GetMapping("/{id}")
    public ResponseEntity<LSContentType> getContentTypeById(@PathVariable("id") Long id) {
        Optional<LSContentType> contentType = contentTypeRepository.findById(id);
        return contentType.map(ResponseEntity::ok)
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new content type
    @PostMapping
    public ResponseEntity<LSContentType> createContentType(@RequestBody LSContentType contentType) {
        try {
            LSContentType savedContentType = contentTypeRepository.save(contentType);
            return new ResponseEntity<>(savedContentType, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing content type
    @PutMapping("/{id}")
    public ResponseEntity<LSContentType> updateContentType(
            @PathVariable("id") Long id, @RequestBody LSContentType updatedContentType) {

        Optional<LSContentType> existingContentType = contentTypeRepository.findById(id);
        if (existingContentType.isPresent()) {
            LSContentType contentType = existingContentType.get();
            contentType.setType(updatedContentType.getType());
            contentType.setName(updatedContentType.getName());

            LSContentType savedContentType = contentTypeRepository.save(contentType);
            return new ResponseEntity<>(savedContentType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a content type by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteContentType(@PathVariable("id") Long id) {
        try {
            if (contentTypeRepository.existsById(id)) {
                contentTypeRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
