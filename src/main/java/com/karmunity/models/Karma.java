package com.karmunity.models;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Karma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "giver_id", nullable = false)
    private Member giver;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;

    @Column(nullable = false)
    private int karmaPoints;

    @Column(nullable = false)
    private String karmaAct;

    @Column(nullable = false)
    private LocalDateTime sentAt;
}