package com.karmunity.controllers;

import com.karmunity.models.Member;
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
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        if (member.getPronouns() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Member savedMember = memberRepository.save(member);
        return ResponseEntity.status(201).body(savedMember);
    }

    // Update an existing member
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
            member.setHasPet(memberDetails.isHasPet());

            if (memberDetails.getPronouns() != null) {
                member.setPronouns(memberDetails.getPronouns());
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