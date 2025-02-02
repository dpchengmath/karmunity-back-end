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
        // Check if the Karmunity name already exists
        Optional<Karmunity> existingKarmunity = karmunityRepository.findByKarmunityNameIgnoreCase(karmunity.getKarmunityName());
        if (existingKarmunity.isPresent()) {
            return ResponseEntity.badRequest().body(null); // Return a 400 Bad Request if name exists
        }

        Karmunity savedKarmunity = karmunityRepository.save(karmunity);
        return ResponseEntity.ok(savedKarmunity);
    }

    // Get all Karmunities or filter by karmunityName
    @GetMapping
    public ResponseEntity<List<Karmunity>> getKarmunities(@RequestParam(required = false) String karmunity) {
        if (karmunity != null && !karmunity.isEmpty()) {
            Optional<Karmunity> foundKarmunity = karmunityRepository.findByKarmunityNameIgnoreCase(karmunity);
            return foundKarmunity.map(k -> ResponseEntity.ok(List.of(k)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.ok(karmunityRepository.findAll());
    }

    // Get a Karmunity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Karmunity> getKarmunityById(@PathVariable Long id) {
        Optional<Karmunity> karmunity = karmunityRepository.findById(id);
        return karmunity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get Karmunity by Name
    @GetMapping("/name/{karmunityName}")
    public ResponseEntity<Karmunity> getKarmunityByName(@PathVariable String karmunityName) {
        Optional<Karmunity> karmunity = karmunityRepository.findByKarmunityNameIgnoreCase(karmunityName);
        return karmunity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get a Karmunity by Name with its Members
    @GetMapping("/{karmunityName}/members")
    public ResponseEntity<Karmunity> getKarmunityWithMembers(@PathVariable String karmunityName) {
        Optional<Karmunity> karmunity = karmunityRepository.findByKarmunityNameIgnoreCase(karmunityName);

        if (karmunity.isPresent()) {
            // Accessing the Karmunity and its members
            Karmunity karmunityWithMembers = karmunity.get();

            // This will automatically include the members from the @ManyToMany relationship
            return ResponseEntity.ok(karmunityWithMembers);
        } else {
            return ResponseEntity.notFound().build();
        }
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