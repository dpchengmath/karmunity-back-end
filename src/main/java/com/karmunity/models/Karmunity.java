package com.karmunity.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Karmunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String karmunityName;

    @ManyToMany(mappedBy = "karmunities")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "karmunity")
    private List<KarmunityInvitation> karmunityInvitations = new ArrayList<>();

}