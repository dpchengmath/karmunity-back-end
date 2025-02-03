package com.karmunity.dto;

import com.karmunity.models.KarmunityInvitation;
import lombok.Getter;

@Getter
public class KarmunityInvitationDTO {
    private Long id;
    private String invitationStatus;

    public KarmunityInvitationDTO(KarmunityInvitation invitation) {
        this.id = invitation.getId();
        this.invitationStatus = String.valueOf(invitation.getInvitationStatus());
    }
}