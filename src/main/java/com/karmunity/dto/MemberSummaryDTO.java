package com.karmunity.dto;

import com.karmunity.models.Member;
import lombok.Getter;

@Getter
public class MemberSummaryDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String pronouns;

    public MemberSummaryDTO(Member member) {
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.username = member.getUsername();
        this.pronouns = member.getPronouns().toString();
    }
}