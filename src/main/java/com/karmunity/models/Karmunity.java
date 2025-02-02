package com.karmunity.models;

import com.karmunity.KarmunityApplication;
import lombok.Data;
import java.util.*;
import java.time.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.boot.SpringApplication;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Karmunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String karmunityName;

    @ManyToMany(mappedBy = "karmunities")
    @JsonIgnore
    private List<Member> members;

    @OneToMany(mappedBy = "karmunity")
    private List<KarmunityInvitation> karmunityInvitations = new ArrayList<>();
}