package com.karmunity.controllers;

import com.karmunity.models.Shoutout;
import com.karmunity.repositories.ShoutoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shoutouts")
public class ShoutoutController {

    @Autowired
    private ShoutoutRepository shoutoutRepository;

    // Get all Shoutouts
    @GetMapping
    public List<Shoutout> getAllShoutouts() {
        return shoutoutRepository.findAll();
    }

    // Get a specific Shoutout by ID
    @GetMapping("/{id}")
    public ResponseEntity<Shoutout> getShoutoutById(@PathVariable Long id) {
        Optional<Shoutout> shoutout = shoutoutRepository.findById(id);
        return shoutout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new Shoutout
    @PostMapping("/create")
    public ResponseEntity<Shoutout> createShoutoutContent(@RequestBody Shoutout shoutout) {
        if (shoutout.getCreatedAt() == null) {
            shoutout.setCreatedAt(java.time.LocalDateTime.now());
        }

        if (shoutout.getSender() == null || shoutout.getReceiver() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Shoutout savedShoutout = shoutoutRepository.save(shoutout);
        return ResponseEntity.status(201).body(savedShoutout);
    }

    // Update a shoutout's content
    @PutMapping("/{id}")
    public ResponseEntity<Shoutout> updateShoutout(@PathVariable Long id, @RequestBody String newShoutoutContent) {
        Optional<Shoutout> existingShoutout = shoutoutRepository.findById(id);

        if (existingShoutout.isPresent()) {
            Shoutout shoutout = existingShoutout.get();
            shoutout.setShoutoutContent(newShoutoutContent);
            shoutout.setCreatedAt(java.time.LocalDateTime.now());
            Shoutout updatedShoutout = shoutoutRepository.save(shoutout);
            return ResponseEntity.ok(updatedShoutout);
        }

        return ResponseEntity.notFound().build();
    }

    // Delete a Shoutout
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoutout(@PathVariable Long id) {
        Optional<Shoutout> shoutout = shoutoutRepository.findById(id);

        if (shoutout.isPresent()) {
            shoutoutRepository.delete(shoutout.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}