package com.karmunity.dto;

import com.karmunity.models.Karmunity;
import com.karmunity.models.Member;
import com.karmunity.models.Pronouns;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Pronouns pronouns;
    private String email;
    private LocalDate birthday;
    private String username;
    private String status;
    private Boolean hasPet;
    private int karma;
    private List<String> karmunities;
    private List<KarmunityInvitationDTO> sentInvitations;
    private List<KarmunityInvitationDTO> receivedInvitations;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.pronouns = member.getPronouns();
        this.email = member.getEmail();
        this.birthday = member.getBirthday();
        this.username = member.getUsername();
        this.status = member.getStatus();
        this.hasPet = member.getHasPet();
        this.karma = member.getKarma();

        // Extract only karmunity names
        this.karmunities = member.getKarmunities().stream()
                .map(Karmunity::getKarmunityName)
                .collect(Collectors.toList());

        // Convert invitations with renamed key
        this.sentInvitations = member.getSentInvitations().stream()
                .map(KarmunityInvitationDTO::new)
                .collect(Collectors.toList());

        this.receivedInvitations = member.getReceivedInvitations().stream()
                .map(KarmunityInvitationDTO::new)
                .collect(Collectors.toList());
    }
}