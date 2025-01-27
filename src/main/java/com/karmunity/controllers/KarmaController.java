package com.karmunity.controllers;

import com.karmunity.models.Karma;
import com.karmunity.repositories.KarmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/karma")
public class KarmaController {

    @Autowired
    private KarmaRepository karmaRepository;

    // Get all Karma records endpoint
    @GetMapping
    public List<Karma> getAllKarma() {
        return karmaRepository.findAll();
    }

    // Get a single Karma record by ID
    @GetMapping("/{id}")
    public ResponseEntity<Karma> getKarmaById(@PathVariable Long id) {
        Optional<Karma> karma = karmaRepository.findById(id);
        return karma.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new Karma record
    @PostMapping("/create")
    public ResponseEntity<Karma> createKarma(@RequestBody Karma karma) {
        Karma savedKarma = karmaRepository.save(karma);
        return ResponseEntity.status(201).body(savedKarma);
    }

    // Endpoint to update an existing Karma record
    @PutMapping("/{id}")
    public ResponseEntity<Karma> updateKarma(@PathVariable Long id, @RequestBody Karma karmaDetails) {
        Optional<Karma> existingKarma = karmaRepository.findById(id);

        if (existingKarma.isPresent()) {
            Karma karma = existingKarma.get();
            karma.setKarmaPoints(karmaDetails.getKarmaPoints());
            karma.setKarmaAct(karmaDetails.getKarmaAct());
            karma.setSentAt(karmaDetails.getSentAt());
            karma.setGiver(karmaDetails.getGiver());
            karma.setReceiver(karmaDetails.getReceiver());
            karmaRepository.save(karma);
            return ResponseEntity.ok(karma);
        }

        return ResponseEntity.notFound().build();
    }

    // Delete a Karma record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKarma(@PathVariable Long id) {
        Optional<Karma> karma = karmaRepository.findById(id);

        if (karma.isPresent()) {
            karmaRepository.delete(karma.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}