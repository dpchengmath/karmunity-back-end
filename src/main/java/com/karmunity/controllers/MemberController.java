package com.karmunity.controllers;

import com.karmunity.models.Member;
import com.karmunity.models.Pronouns;
import com.karmunity.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    // Get all members
    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Get a member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new member
    @PostMapping("/create")
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

            // Set pronouns, default to OTHER if null
            if (memberDetails.getPronouns() != null) {
                member.setPronouns(memberDetails.getPronouns());  // Use the Pronouns enum directly
            } else {
                member.setPronouns(Pronouns.OTHER);  // Default to "other" if no pronouns are provided
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