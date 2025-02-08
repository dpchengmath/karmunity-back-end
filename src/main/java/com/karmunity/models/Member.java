package com.karmunity.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Setter
    @Enumerated(EnumType.STRING)
    @NotNull
    private Pronouns pronouns;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private LocalDate birthday;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    private String status;

    private Boolean hasPet = false;

    // Ensure only one field for karmaStats
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private KarmaStats karmaStats;

    // Karma logic
    public void addKarma(KarmaAct karmaAct, int points) {
        if (this.karmaStats == null) {
            this.karmaStats = new KarmaStats();
        }

        switch (karmaAct) {
            case ACCOUNTABILITY -> karmaStats.setACCOUNTABILITY(karmaStats.getACCOUNTABILITY() + points);
            case TEAMWORK -> karmaStats.setTEAMWORK(karmaStats.getTEAMWORK() + points);
            case SERVICE -> karmaStats.setSERVICE(karmaStats.getSERVICE() + points);
            case ENCOURAGEMENT -> karmaStats.setENCOURAGEMENT(karmaStats.getENCOURAGEMENT() + points);
            case INSPIRATION -> karmaStats.setINSPIRATION(karmaStats.getINSPIRATION() + points);
            case INITIATIVE -> karmaStats.setINITIATIVE(karmaStats.getINITIATIVE() + points);
            case PATIENCE -> karmaStats.setPATIENCE(karmaStats.getPATIENCE() + points);
            case RELIABILITY -> karmaStats.setRELIABILITY(karmaStats.getRELIABILITY() + points);
            case AUTHENTICITY -> karmaStats.setAUTHENTICITY(karmaStats.getAUTHENTICITY() + points);
            case KNOWLEDGE -> karmaStats.setKNOWLEDGE(karmaStats.getKNOWLEDGE() + points);
            case THOUGHTFULNESS -> karmaStats.setTHOUGHTFULNESS(karmaStats.getTHOUGHTFULNESS() + points);
            case GENEROSITY -> karmaStats.setGENEROSITY(karmaStats.getGENEROSITY() + points);
            case PRODUCTIVITY -> karmaStats.setPRODUCTIVITY(karmaStats.getPRODUCTIVITY() + points);
            case QUALITY_TIME -> karmaStats.setQUALITY_TIME(karmaStats.getQUALITY_TIME() + points);
            case OTHER -> karmaStats.setOTHER(karmaStats.getOTHER() + points);
        }
    }

    @ManyToMany
    @JoinTable(
            name = "member_karmunities",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "karmunity_id")
    )
    @JsonIgnore
    private List<Karmunity> karmunities = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<KarmunityInvitation> sentInvitations = new ArrayList<>();

    @OneToMany(mappedBy = "recipient")
    private List<KarmunityInvitation> receivedInvitations = new ArrayList<>();
}