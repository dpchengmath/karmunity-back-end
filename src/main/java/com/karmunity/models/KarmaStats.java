package com.karmunity.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class KarmaStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private int ACCOUNTABILITY;
    private int TEAMWORK;
    private int SERVICE;
    private int ENCOURAGEMENT;
    private int INSPIRATION;
    private int INITIATIVE;
    private int PATIENCE;
    private int RELIABILITY;
    private int AUTHENTICITY;
    private int KNOWLEDGE;
    private int THOUGHTFULNESS;
    private int GENEROSITY;
    private int PRODUCTIVITY;
    private int QUALITY_TIME;
    private int OTHER;
}