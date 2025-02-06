package com.karmunity.controllers;

import com.karmunity.dto.MemberDTO;
import com.karmunity.models.KarmaAct;
import com.karmunity.models.KarmaEntry;
import com.karmunity.models.KarmaStats;
import com.karmunity.models.Member;
import com.karmunity.models.Pronouns;
import com.karmunity.repositories.KarmunityRepository;
import com.karmunity.repositories.MemberRepository;
import com.karmunity.repositories.KarmaStatsRepository;
import com.karmunity.repositories.KarmaEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private KarmunityRepository karmunityRepository;

    @Autowired
    private KarmaStatsRepository karmaStatsRepository;

    @Autowired
    private KarmaEntryRepository karmaEntryRepository;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> searchMembers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Long id) {

        List<Member> members = new ArrayList<>();

        if (username != null && !username.isEmpty()) {
            Optional<Member> member = memberRepository.findByUsername(username);
            member.ifPresent(members::add);
        } else if (id != null) {
            Optional<Member> member = memberRepository.findById(id);
            member.ifPresent(members::add);
        } else {
            members = memberRepository.findAll();
        }

        if (!members.isEmpty()) {
            List<MemberDTO> response = members.stream()
                    .map(MemberDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all members in a specific Karmunity
    @GetMapping("/karmunity/{karmunityName}")
    public ResponseEntity<List<Member>> getMembersByKarmunity(@PathVariable String karmunityName) {
        List<Member> members = memberRepository.findByKarmunities_KarmunityName(karmunityName);
        if (!members.isEmpty()) {
            return ResponseEntity.ok(members);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all Karmunities a member is part of
    @GetMapping("/{id}/karmunities")
    public ResponseEntity<List<String>> getKarmunitiesByMember(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            List<String> karmunityNames = new ArrayList<>();
            member.get().getKarmunities().forEach(k -> karmunityNames.add(k.getKarmunityName()));
            return ResponseEntity.ok(karmunityNames);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new member
    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody Member memberDetails) {
        Optional<Member> existingMember = memberRepository.findByUsername(memberDetails.getUsername());
        if (existingMember.isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken.");
        }

        try {
            Member member = new Member();

            if (memberDetails.getHasPet() == null) {
                member.setHasPet(false);
            } else {
                member.setHasPet(memberDetails.getHasPet());
            }

            member.setFirstName(memberDetails.getFirstName());
            member.setLastName(memberDetails.getLastName());
            member.setEmail(memberDetails.getEmail());
            member.setBirthday(memberDetails.getBirthday());
            member.setUsername(memberDetails.getUsername());
            member.setPassword(memberDetails.getPassword());
            member.setStatus(memberDetails.getStatus());
            member.setKarma(memberDetails.getKarma());

            if (memberDetails.getPronouns() == null) {
                member.setPronouns(Pronouns.OTHER);
            } else {
                member.setPronouns(memberDetails.getPronouns());
            }

            memberRepository.save(member);
            return ResponseEntity.ok(member);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to give karma
    @PostMapping("/give-karma")
    public ResponseEntity<String> giveKarma(@RequestBody KarmaEntry karmaEntry) {
        // Find the karma receiver by ID
        Optional<Member> karmaReceiverOpt = memberRepository.findById(karmaEntry.getKarmaReceiver().getId());
        if (!karmaReceiverOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Karma receiver not found");
        }

        Member karmaReceiver = karmaReceiverOpt.get();
        KarmaStats karmaStats = karmaReceiver.getKarmaStats();

        // If karmaStats doesn't exist, create it
        if (karmaStats == null) {
            karmaStats = new KarmaStats();
            karmaStats.setMember(karmaReceiver);
            karmaReceiver.setKarmaStats(karmaStats);
        }

        // Use the custom fromString method for case-insensitive matching
        KarmaAct karmaAct;
        try {
            karmaAct = KarmaAct.fromString(String.valueOf(karmaEntry.getKarmaAct()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid Karma Act");
        }

        // Increment the appropriate karma act based on the entry
        karmaStats.addKarma(karmaAct, karmaEntry.getKarma());

        // Save the updated KarmaStats and KarmaEntry
        karmaStatsRepository.save(karmaStats);
        karmaEntry.setKarmaStats(karmaStats);
        karmaEntryRepository.save(karmaEntry);

        return ResponseEntity.ok("Karma successfully given");
    }


    // Update a member
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        Optional<Member> existingMember = memberRepository.findById(id);

        if (existingMember.isPresent()) {
            Member member = existingMember.get();

            member.setFirstName(memberDetails.getFirstName());
            member.setLastName(memberDetails.getLastName());
            member.setEmail(memberDetails.getEmail());
            member.setStatus(memberDetails.getStatus());
            member.setKarma(memberDetails.getKarma());
            member.setHasPet(memberDetails.getHasPet());

            if (memberDetails.getPronouns() != null) {
                member.setPronouns(memberDetails.getPronouns());
            } else {
                member.setPronouns(Pronouns.OTHER);
            }

            memberRepository.save(member);
            return ResponseEntity.ok(member);
        }

        return ResponseEntity.notFound().build();
    }

    // Update a member's karma
    @PutMapping("/{id}/karma")
    public ResponseEntity<Member> updateKarma(@PathVariable Long id, @RequestParam KarmaAct karmaAct, @RequestParam int points) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Member member = optionalMember.get();
        member.addKarma(karmaAct, points);
        memberRepository.save(member);

        return ResponseEntity.ok(member);
    }

    // Delete a member
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);

        if (member.isPresent()) {
            memberRepository.delete(member.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}