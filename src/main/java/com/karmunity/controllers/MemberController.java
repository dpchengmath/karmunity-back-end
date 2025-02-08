package com.karmunity.controllers;

import com.karmunity.dto.MemberDTO;
import com.karmunity.models.*;
import com.karmunity.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private KarmaStatsRepository karmaStatsRepository;

    @Autowired
    private KarmaEntryRepository karmaEntryRepository;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Long id) {
        List<Member> members = (username != null) ?
                memberRepository.findByUsername(username).stream().toList() :
                (id != null) ? memberRepository.findById(id).stream().toList() :
                        memberRepository.findAll();

        if (!members.isEmpty()) {
            List<MemberDTO> response = members.stream().map(MemberDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new member
    @PostMapping("")
    public ResponseEntity<?> createMember(@RequestBody Member memberDetails) {
        try {
            Member member = new Member();

            // Ensure hasPet is not null
            if (memberDetails.getHasPet() == null) {
                member.setHasPet(false);  // Default to false if null
            } else {
                member.setHasPet(memberDetails.getHasPet());
            }

            // Set other fields from memberDetails
            member.setFirstName(memberDetails.getFirstName());
            member.setLastName(memberDetails.getLastName());
            member.setEmail(memberDetails.getEmail());
            member.setBirthday(memberDetails.getBirthday());
            member.setUsername(memberDetails.getUsername());
            member.setPassword(memberDetails.getPassword());
            member.setStatus(memberDetails.getStatus());
            member.setKarma(memberDetails.getKarma());

            if (memberDetails.getPronouns() == null) {
                member.setPronouns(Pronouns.OTHER);  // Default to "other" if no pronouns are provided
            } else {
                member.setPronouns(memberDetails.getPronouns());
            }

            memberRepository.save(member);
            return ResponseEntity.ok(member);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @PostMapping("/give-karma")
    public ResponseEntity<String> giveKarma(@RequestBody KarmaEntry karmaEntry) {
        Optional<Member> receiverOpt = memberRepository.findById(karmaEntry.getKarmaReceiver().getId());
        if (receiverOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Karma receiver not found");
        }

        Member receiver = receiverOpt.get();
        KarmaStats karmaStats = karmaStatsRepository.findByMemberId(receiver.getId()).orElseGet(() -> {
            KarmaStats newStats = new KarmaStats();
            newStats.setMember(receiver);
            return karmaStatsRepository.save(newStats);
        });

        karmaStats.addKarma(karmaEntry.getKarmaAct(), karmaEntry.getKarma());
        karmaStatsRepository.save(karmaStats);

        karmaEntry.setKarmaStats(karmaStats);
        karmaEntryRepository.save(karmaEntry);

        return ResponseEntity.ok("Karma successfully given");
    }

    @PutMapping("/{id}/karma")
    public ResponseEntity<KarmaStats> updateKarma(
            @PathVariable Long id,
            @RequestParam KarmaAct karmaAct,
            @RequestParam int points) {

        Optional<KarmaStats> optionalStats = karmaStatsRepository.findByMemberId(id);
        if (optionalStats.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        KarmaStats karmaStats = optionalStats.get();
        karmaStats.addKarma(karmaAct, points);
        karmaStatsRepository.save(karmaStats);

        return ResponseEntity.ok(karmaStats);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}