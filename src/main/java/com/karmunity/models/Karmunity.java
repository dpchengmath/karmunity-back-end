package com.karmunity.models;

import com.karmunity.KarmunityApplication;
import lombok.Data;
import java.util.*;
import java.time.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.boot.SpringApplication;

@Data
@Entity
public class Karmunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "karmunities")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "karmunity")
    private List<KarmunityInvitation> karmunityInvitations = new ArrayList<>();
}