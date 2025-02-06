package com.karmunity.controllers;

import com.karmunity.models.Karma;
import com.karmunity.models.KarmaEntry;
import com.karmunity.repositories.KarmaEntryRepository;
import com.karmunity.repositories.KarmaRepository;
import com.karmunity.models.KarmaStats;
import com.karmunity.repositories.KarmaStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/karma")
public class KarmaController {

    @Autowired
    private KarmaRepository karmaRepository;

    @Autowired
    private KarmaStatsRepository karmaStatsRepository;

    @Autowired
    private KarmaEntryRepository karmaEntryRepository;  // Add this line

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

    @PostMapping("/give-karma")
    public ResponseEntity<KarmaEntry> giveKarma(@RequestBody KarmaEntry karmaEntry) {
        // Check if KarmaStats exists for the receiver
        Optional<KarmaStats> karmaStatsOptional = karmaStatsRepository.findByMemberId(karmaEntry.getKarmaReceiver().getId());

        KarmaStats karmaStats;
        if (karmaStatsOptional.isPresent()) {
            karmaStats = karmaStatsOptional.get();
        } else {
            // If KarmaStats doesn't exist, create a new one
            karmaStats = new KarmaStats();
            karmaStats.setMember(karmaEntry.getKarmaReceiver());
        }

        // Update the karma stats with the karma points
        karmaStats.addKarma(karmaEntry.getKarmaAct(), karmaEntry.getKarma());

        // Save the KarmaStats
        karmaStatsRepository.save(karmaStats);

        // Save the KarmaEntry
        karmaEntry.setKarmaStats(karmaStats);  // Make sure KarmaStats is set on the KarmaEntry
        karmaEntryRepository.save(karmaEntry);  // Save KarmaEntry using the KarmaEntryRepository

        // Return the KarmaEntry with a 201 status code
        return ResponseEntity.status(201).body(karmaEntry);
    }



    // Endpoint to update an existing Karma record
    @PutMapping("/{id}/receive-karma")
    public ResponseEntity<Karma> receiveKarma(@PathVariable Long id, @RequestBody Karma karmaDetails) {
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