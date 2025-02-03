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

    @Setter
    @Getter
    private int karma = 0;

    public void addKarma(KarmaAct karmaAct) {
        this.karma += karmaAct.getPoints();
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