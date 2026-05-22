package com.example.longevitysync.controller;

import com.example.longevitysync.model.LSQuestionnaire;
import com.example.longevitysync.repository.LSQuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questionnaires")
public class LSQuestionnaireController {

    @Autowired
    private LSQuestionnaireRepository questionnaireRepository;

    // Get all questionnaires
    @GetMapping
    public ResponseEntity<List<LSQuestionnaire>> getAllQuestionnaires() {
        List<LSQuestionnaire> questionnaires = questionnaireRepository.findAll();
        return new ResponseEntity<>(questionnaires, HttpStatus.OK);
    }

    // Get a specific questionnaire by ID
    @GetMapping("/{id}")
    public ResponseEntity<LSQuestionnaire> getQuestionnaireById(@PathVariable("id") Long id) {
        Optional<LSQuestionnaire> questionnaire = questionnaireRepository.findById(id);
        return questionnaire.map(ResponseEntity::ok)
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new questionnaire
    @PostMapping
    public ResponseEntity<LSQuestionnaire> createQuestionnaire(@RequestBody LSQuestionnaire questionnaire) {
        try {
            LSQuestionnaire savedQuestionnaire = questionnaireRepository.save(questionnaire);
            return new ResponseEntity<>(savedQuestionnaire, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing questionnaire
    @PutMapping("/{id}")
    public ResponseEntity<LSQuestionnaire> updateQuestionnaire(
            @PathVariable("id") Long id, @RequestBody LSQuestionnaire updatedQuestionnaire) {

        Optional<LSQuestionnaire> existingQuestionnaire = questionnaireRepository.findById(id);
        if (existingQuestionnaire.isPresent()) {
            LSQuestionnaire questionnaire = existingQuestionnaire.get();
            // questionnaire.setQuestion(updatedQuestionnaire.getQuestion());
            // questionnaire.setAnswer(updatedQuestionnaire.getAnswer());

            LSQuestionnaire savedQuestionnaire = questionnaireRepository.save(questionnaire);
            return new ResponseEntity<>(savedQuestionnaire, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a questionnaire by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQuestionnaire(@PathVariable("id") Long id) {
        try {
            if (questionnaireRepository.existsById(id)) {
                questionnaireRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
