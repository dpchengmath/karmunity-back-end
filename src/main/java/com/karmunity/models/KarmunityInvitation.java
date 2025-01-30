package com.karmunity.models;
import com.karmunity.models.KarmunityInvitationStatus;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Entity
public class KarmunityInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Member recipient;

    @ManyToOne
    @JoinColumn(name = "karmunity_id", nullable = false)
    private Karmunity karmunity;

    @NotNull
    private LocalDateTime sentAt;

    @Enumerated(EnumType.STRING)
    @NotNull
    private KarmunityInvitationStatus invitationStatus;
}

