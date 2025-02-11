package com.karmunity.controllers;

import com.karmunity.dto.KarmaEntryResponseDTO;
import com.karmunity.dto.MemberSummaryDTO;
import com.karmunity.models.KarmaEntry;
import com.karmunity.models.KarmaStats;
import com.karmunity.models.Member;
import com.karmunity.repositories.MemberRepository;
import com.karmunity.repositories.KarmaEntryRepository;
import com.karmunity.repositories.KarmaStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/karma")
@CrossOrigin(origins = {"http://localhost:5173", "https://karmunity-ffdd6bea6ef1.herokuapp.com", "https://www.karmunity.com"})
public class KarmaController {

    @Autowired
    private KarmaEntryRepository karmaEntryRepository;

    @Autowired
    private KarmaStatsRepository karmaStatsRepository;

    @Autowired
    private final MemberRepository memberRepository;

    // Get KarmaEntry by karma entry ID
    @GetMapping("/give-karma/{karmaEntryId}")
    public ResponseEntity<KarmaEntryResponseDTO> getKarmaEntry(@PathVariable Long karmaEntryId) {
        KarmaEntry karmaEntry = karmaEntryRepository.findById(karmaEntryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "KarmaEntry not found"));

        // Convert Member to MemberSummaryDTO
        MemberSummaryDTO karmaGiverDTO = new MemberSummaryDTO(karmaEntry.getKarmaGiver());
        MemberSummaryDTO karmaReceiverDTO = new MemberSummaryDTO(karmaEntry.getKarmaReceiver());

        // Convert KarmaEntry to DTO
        KarmaEntryResponseDTO responseDTO = new KarmaEntryResponseDTO(
                karmaEntry.getId(),
                karmaEntry.getKarmaAct().toString(),
                karmaEntry.getKudos(),
                karmaEntry.getKarma(),
                karmaGiverDTO,
                karmaReceiverDTO,
                karmaEntry.getKarmaStats()
        );

        return ResponseEntity.ok(responseDTO);
    }

    // Get all KarmaStats records
    @GetMapping
    public ResponseEntity<List<KarmaStats>> getAllKarmaStats() {
        return ResponseEntity.ok(karmaStatsRepository.findAll());
    }

    // Get KarmaStats by member ID
    @GetMapping("/karma-stats/{memberId}")
    public ResponseEntity<KarmaStats> getKarmaStatsByMemberId(@PathVariable Long memberId) {
        Optional<KarmaStats> karmaStats = karmaStatsRepository.findByMemberId(memberId);
        return karmaStats.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public KarmaController(KarmaEntryRepository karmaEntryRepository, MemberRepository memberRepository) {
        this.karmaEntryRepository = karmaEntryRepository;
        this.memberRepository = memberRepository;
    }

    // Get kudos by username
    @GetMapping("/kudos/{username}")
    public List<Map<String, Object>> getKudosReceivedByMember(@PathVariable String username) {
        Member receiver = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<KarmaEntry> karmaEntries = karmaEntryRepository.findByKarmaReceiver(receiver);

        return karmaEntries.stream()
                .map(karmaEntry -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("kudos", karmaEntry.getKudos());
                    response.put("karmaGiver", new MemberSummaryDTO(karmaEntry.getKarmaGiver()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    // POST method to give karma to a member
    @PostMapping("/give-karma")
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