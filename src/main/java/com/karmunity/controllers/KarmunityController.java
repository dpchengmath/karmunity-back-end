package com.karmunity.controllers;

import com.karmunity.dto.KarmunityDTO;
import com.karmunity.models.Karmunity;
import com.karmunity.models.Member;
import com.karmunity.repositories.KarmunityRepository;
import com.karmunity.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/karmunities")
@CrossOrigin(origins = {"http://localhost:5173", "https://karmunity-ffdd6bea6ef1.herokuapp.com", "https://www.karmunity.com"})
public class KarmunityController {

    @Autowired
    private KarmunityRepository karmunityRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Create a new Karmunity
    @PostMapping
    public ResponseEntity<Karmunity> createKarmunity(@RequestBody Map<String, Object> requestBody) {
        String karmunityName = (String) requestBody.get("karmunityName");
        List<Long> memberIds = (List<Long>) requestBody.get("members");
        List<Member> members = memberRepository.findAllById(memberIds);

        Karmunity karmunity = new Karmunity();
        karmunity.setKarmunityName(karmunityName);

        karmunity.setMembers(members);  // Add the members to the Karmunity's members list

        // Also update each Member's list of Karmunities (the reverse relationship)
        for (Member member : members) {
            member.getKarmunities().add(karmunity);  // Add the Karmunity to each Member's karmunities list
        }

        Karmunity savedKarmunity = karmunityRepository.save(karmunity);
        memberRepository.saveAll(members);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedKarmunity);
    }

    @GetMapping
    public ResponseEntity<List<KarmunityDTO>> getKarmunities(@RequestParam(required = false) String karmunity) {
        List<Karmunity> karmunities;

        if (karmunity != null && !karmunity.isEmpty()) {
            karmunities = karmunityRepository.findByKarmunityNameIgnoreCase(karmunity);
        } else {
            karmunities = karmunityRepository.findAll();
        }

        List<KarmunityDTO> response = karmunities.stream()
                .map(KarmunityDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Get a Karmunity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Karmunity> getKarmunityById(@PathVariable Long id) {
        Optional<Karmunity> karmunity = karmunityRepository.findById(id);
        return karmunity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Karmunity
    @PutMapping("/{id}")
    public ResponseEntity<Karmunity> updateKarmunity(@PathVariable Long id, @RequestBody Karmunity karmunityDetails) {
        Optional<Karmunity> optionalKarmunity = karmunityRepository.findById(id);
        if (!optionalKarmunity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Karmunity karmunity = optionalKarmunity.get();
        karmunity.setKarmunityName(karmunityDetails.getKarmunityName());
        karmunityRepository.save(karmunity);
        return ResponseEntity.ok(karmunity);
    }

    // Delete a Karmunity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKarmunity(@PathVariable Long id) {
        Optional<Karmunity> karmunity = karmunityRepository.findById(id);
        if (!karmunity.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        karmunityRepository.delete(karmunity.get());
        return ResponseEntity.noContent().build();
    }
}