package com.example.longevitysync.controller;

import com.example.longevitysync.model.LSContent;
import com.example.longevitysync.repository.LSContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contents")
public class LSContentController {

    @Autowired
    private LSContentRepository contentRepository;

    // Get all contents
    @GetMapping
    public ResponseEntity<List<LSContent>> getAllContents() {
        List<LSContent> contents = contentRepository.findAll();
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    // Get a specific content by ID
    @GetMapping("/{id}")
    public ResponseEntity<LSContent> getContentById(@PathVariable("id") Long id) {
        Optional<LSContent> content = contentRepository.findById(id);
        return content.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new content
    @PostMapping
    public ResponseEntity<LSContent> createContent(@RequestBody LSContent content) {
        try {
            LSContent savedContent = contentRepository.save(content);
            return new ResponseEntity<>(savedContent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing content
    @PutMapping("/{id}")
    public ResponseEntity<LSContent> updateContent(
            @PathVariable("id") Long id, @RequestBody LSContent updatedContent) {

        Optional<LSContent> existingContent = contentRepository.findById(id);
        if (existingContent.isPresent()) {
            LSContent content = existingContent.get();
            // content.setContentTypeId(updatedContent.getContentTypeId());
            content.setContentLocation(updatedContent.getContentLocation());
            // content.setImageId(updatedContent.getImageId());

            LSContent savedContent = contentRepository.save(content);
            return new ResponseEntity<>(savedContent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a content by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteContent(@PathVariable("id") Long id) {
        try {
            if (contentRepository.existsById(id)) {
                contentRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
