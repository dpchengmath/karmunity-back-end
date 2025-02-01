package com.karmunity.controllers;

import com.karmunity.models.Member;
import com.karmunity.models.Pronouns;
import com.karmunity.repositories.KarmunityRepository;
import com.karmunity.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private KarmunityRepository karmunityRepository;

    @GetMapping
    public ResponseEntity<List<Member>> searchMembers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Long id) {

        List<Member> members = new ArrayList<>();

        // If username is provided, search by username
        if (username != null && !username.isEmpty()) {
            Optional<Member> member = memberRepository.findByUsername(username);
            member.ifPresent(members::add);
        }
        // If id is provided, search by id
        else if (id != null) {
            Optional<Member> member = memberRepository.findById(id);
            member.ifPresent(members::add);
        }
        // If neither username nor id is provided, return all members
        else {
            members = memberRepository.findAll();
        }

        if (!members.isEmpty()) {
            return ResponseEntity.ok(members);
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