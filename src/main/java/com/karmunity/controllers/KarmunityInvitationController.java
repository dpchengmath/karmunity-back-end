package com.karmunity.controllers;

import com.karmunity.models.KarmunityInvitation;
import com.karmunity.models.KarmunityInvitationStatus;
import com.karmunity.repositories.KarmunityInvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invitations")
public class KarmunityInvitationController {

    @Autowired
    private KarmunityInvitationRepository invitationRepository;

    // Get all Karmunity Invitations
    @GetMapping
    public List<KarmunityInvitation> getAllInvitations() {
        return invitationRepository.findAll();
    }

    // Get a Karmunity Invitation by ID
    @GetMapping("/{id}")
    public ResponseEntity<KarmunityInvitation> getInvitationById(@PathVariable Long id) {
        Optional<KarmunityInvitation> invitation = invitationRepository.findById(id);
        return invitation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new Karmunity Invitation
    @PostMapping
    public ResponseEntity<KarmunityInvitation> createInvitation(@RequestBody KarmunityInvitation invitation) {
        if (invitation.getSentAt() == null) {
            invitation.setSentAt(java.time.LocalDateTime.now());
        }

        KarmunityInvitation savedInvitation = invitationRepository.save(invitation);
        return ResponseEntity.status(201).body(savedInvitation);
    }

    // Update the status of an existing Karmunity Invitation
    @PutMapping("/{id}/status")
    public ResponseEntity<KarmunityInvitation> updateInvitationStatus(@PathVariable Long id, @RequestBody KarmunityInvitationStatus newStatus) {
        Optional<KarmunityInvitation> existingInvitation = invitationRepository.findById(id);

        if (existingInvitation.isPresent()) {
            KarmunityInvitation invitation = existingInvitation.get();
            invitation.setInvitationStatus(newStatus);
            KarmunityInvitation updatedInvitation = invitationRepository.save(invitation);
            return ResponseEntity.ok(updatedInvitation);
        }

        return ResponseEntity.notFound().build();
    }

    // Delete a Karmunity Invitation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable Long id) {
        Optional<KarmunityInvitation> invitation = invitationRepository.findById(id);

        if (invitation.isPresent()) {
            invitationRepository.delete(invitation.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}