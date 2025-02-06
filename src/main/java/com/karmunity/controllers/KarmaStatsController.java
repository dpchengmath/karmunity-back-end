package com.karmunity.controllers;

import com.karmunity.models.KarmaEntry;
import com.karmunity.models.KarmaStats;
import com.karmunity.repositories.KarmaEntryRepository;
import com.karmunity.repositories.KarmaStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/karma-stats")
public class KarmaStatsController {

    @Autowired
    private KarmaStatsRepository karmaStatsRepository;

    @Autowired
    private KarmaEntryRepository karmaEntryRepository;

    // Get all KarmaStats records
    @GetMapping
    public List<KarmaStats> getAllKarmaStats() {
        List<KarmaStats> stats = karmaStatsRepository.findAll();
        System.out.println("Fetched KarmaStats: " + stats);
        return stats;
    }

    // Get a single KarmaStats record by member ID
    @GetMapping("/{memberId}")
    public ResponseEntity<KarmaStats> getKarmaStatsByMemberId(@PathVariable Long memberId) {
        Optional<KarmaStats> karmaStats = karmaStatsRepository.findByMemberId(memberId);
        return karmaStats.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
        karmaEntry.setKarmaStats(karmaStats);
        karmaEntryRepository.save(karmaEntry);

        // Return the KarmaEntry with a 201 status code
        return ResponseEntity.status(201).body(karmaEntry);
    }
}
