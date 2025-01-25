package com.karmunity.models;

import java.util.*;
import java.time.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Karmunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "karmunities")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "karmunity")
    private List<Invitation> invitations = new ArrayList<>();  // Invitations for this Karmunity

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    // Helper methods for managing members
    public void addMember(Member member) {
        if (!members.contains(member)) {
            members.add(member);
            member.getKarmunities().add(this);
        }
    }

    // Method to remove a member if needed
    public void removeMember(Member member) {
        if (members.contains(member)) {
            members.remove(member);
            member.getKarmunities().remove(this);
        }
    }

    public void acceptInvitation(Invitation invitation) {
        if (invitation.getStatus() == InvitationStatus.PENDING) {
            invitation.setStatus(InvitationStatus.ACCEPTED);
            addMember(invitation.getRecipient());
        }
    }
}