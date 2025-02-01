package com.karmunity.controllers;

import com.karmunity.models.Karmunity;
import com.karmunity.repositories.KarmunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/karmunities")
public class KarmunityController {

    @Autowired
    private KarmunityRepository karmunityRepository;

    // Create a new Karmunity
    @PostMapping
    public ResponseEntity<Karmunity> createKarmunity(@RequestBody Karmunity karmunity) {
        Karmunity savedKarmunity = karmunityRepository.save(karmunity);
        return ResponseEntity.ok(savedKarmunity);
    }

    // Get all Karmunities
    @GetMapping
    public List<Karmunity> getAllKarmunities() {
        return karmunityRepository.findAll();
    }

    // Get a Karmunity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Karmunity> getKarmunityById(@PathVariable Long id) {
        Optional<Karmunity> karmunity = karmunityRepository.findById(id);
        return karmunity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get Karmunity by Name
    @GetMapping("/{karmunityName}")
    public ResponseEntity<Karmunity> getKarmunityByName(@PathVariable String karmunityName) {
        Optional<Karmunity> karmunity = karmunityRepository.findByKarmunityName(karmunityName);
        return karmunity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Karmunity
    @PutMapping("/{id}")
    public ResponseEntity<Karmunity> updateKarmunity(@PathVariable Long id, @RequestBody Karmunity karmunityDetails) {
        Optional<Karmunity> optionalKarmunity = karmunityRepository.findById(id);
        if (!optionalKarmunity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Karmunity karmunity = optionalKarmunity.get();

        karmunity.setKarmunityName(karmunityDetails.getKarmunityName());

        karmunityRepository.save(karmunity);
        return ResponseEntity.ok(karmunity);
    }

    // Delete a Karmunity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKarmunity(@PathVariable Long id) {
        Optional<Karmunity> karmunity = karmunityRepository.findById(id);
        if (!karmunity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        karmunityRepository.delete(karmunity.get());
        return ResponseEntity.noContent().build();
    }
}