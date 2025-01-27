package com.karmunity.models;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;

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

    @NotNull
    private String pronouns;

    @NotNull
    @Email
    private String email;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String status;

    private boolean hasPet;

    private int karma;

    @ManyToMany
    @JoinTable(
            name = "member_karmunities",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "karmunity_id")
    )
    private List<Karmunity> karmunities = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<KarmunityInvitation> sentInvitations = new ArrayList<>();

    @OneToMany(mappedBy = "recipient")
    private List<KarmunityInvitation> receivedInvitations = new ArrayList<>();
}