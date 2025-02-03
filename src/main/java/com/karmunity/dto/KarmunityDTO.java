package com.karmunity.dto;

import com.karmunity.models.Karmunity;
import com.karmunity.models.Member;
import com.karmunity.models.Pronouns;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class KarmunityDTO {
    private Long id;
    private String karmunityName;
    private List<MemberSummaryDTO> members;

    public KarmunityDTO(Karmunity karmunity) {
        this.id = karmunity.getId();
        this.karmunityName = karmunity.getKarmunityName();
        this.members = karmunity.getMembers().stream()
                .map(MemberSummaryDTO::new)
                .collect(Collectors.toList());
    }

    @Getter
    public static class MemberSummaryDTO {
        private String firstName;
        private String lastName;
        private String username;
        private Pronouns pronouns;

        public MemberSummaryDTO(Member member) {
            this.firstName = member.getFirstName();
            this.lastName = member.getLastName();
            this.username = member.getUsername();
            this.pronouns = member.getPronouns();
        }
    }
}