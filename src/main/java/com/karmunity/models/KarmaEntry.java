package com.karmunity.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "karma_entry")
public class KarmaEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KarmaAct karmaAct;

    @Getter
    @Column(nullable = false)
    private int points;

    @Setter
    @ManyToOne
    @JoinColumn(name = "karma_stats_id", nullable = false)
    private KarmaStats karmaStats;

    public KarmaEntry() {
    }

    public KarmaEntry(KarmaAct karmaAct, int points) {
        this.karmaAct = karmaAct;
        this.points = points;
    }
}
