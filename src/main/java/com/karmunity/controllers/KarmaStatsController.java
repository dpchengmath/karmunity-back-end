package com.karmunity.controllers;

import com.karmunity.models.KarmaEntry;
import com.karmunity.models.KarmaStats;
import com.karmunity.repositories.KarmaEntryRepository;
import com.karmunity.repositories.KarmaStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;  // Add this import
import java.util.Optional;

@RestController
@RequestMapping("/karma")
public class KarmaStatsController {

    @Autowired
    private KarmaStatsRepository karmaStatsRepository;

    @Autowired
    private KarmaEntryRepository karmaEntryRepository;

    // Get all KarmaStats records
    @GetMapping
    public ResponseEntity<List<KarmaStats>> getAllKarmaStats() {
        return ResponseEntity.ok(karmaStatsRepository.findAll());
    }

    // Get KarmaStats by member ID
    @GetMapping("/{memberId}")
    public ResponseEntity<KarmaStats> getKarmaStatsByMemberId(@PathVariable Long memberId) {
        Optional<KarmaStats> karmaStats = karmaStatsRepository.findByMemberId(memberId);
        return karmaStats.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST method to give karma to a member
    @PostMapping
    public ResponseEntity<KarmaEntry> giveKarma(@RequestBody KarmaEntry karmaEntry) {
        // Ensure that the receiver's KarmaStats exists
        Optional<KarmaStats> karmaStatsOptional = karmaStatsRepository.findByMemberId(karmaEntry.getKarmaReceiver().getId());
        KarmaStats karmaStats;

        if (karmaStatsOptional.isPresent()) {
            // KarmaStats exists, update the karma
            karmaStats = karmaStatsOptional.get();
        } else {
            // If KarmaStats doesn't exist, create a new one
            karmaStats = new KarmaStats();
            karmaStats.setMember(karmaEntry.getKarmaReceiver());
        }

        // Update the karma points in the KarmaStats object
        karmaStats.addKarma(karmaEntry.getKarmaAct(), karmaEntry.getKarma());

        // Save the updated KarmaStats
        karmaStatsRepository.save(karmaStats);

        // Link the KarmaEntry to the updated KarmaStats
        karmaEntry.setKarmaStats(karmaStats);

        // Save the KarmaEntry to record the action
        karmaEntryRepository.save(karmaEntry);

        // Return the saved KarmaEntry with a 201 status code
        return ResponseEntity.status(201).body(karmaEntry);
    }
}