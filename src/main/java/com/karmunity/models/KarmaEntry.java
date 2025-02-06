package com.karmunity.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
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
    private int karma;

    @Setter
    @ManyToOne
    @JoinColumn(name = "karma_giver_id", nullable = false)
    private Member karmaGiver;

    @Setter
    @ManyToOne
    @JoinColumn(name = "karma_receiver_id", nullable = false)
    private Member karmaReceiver;

    @Setter
    @ManyToOne
    @JoinColumn(name = "karma_stats_id")
    private KarmaStats karmaStats;

    // Constructor
    public KarmaEntry() {
    }

    public KarmaEntry(KarmaAct karmaAct, int karma, Member karmaGiver, Member karmaReceiver) {
        this.karmaAct = karmaAct;
        this.karma = karma;
        this.karmaGiver = karmaGiver;
        this.karmaReceiver = karmaReceiver;
    }
}

