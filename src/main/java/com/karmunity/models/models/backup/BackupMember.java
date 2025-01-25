//package com.karmunity.models;
//import com.karmunity.models.Invitation;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import java.util.*;
//import java.time.LocalDate;
//
//@Entity
//public class Member {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotNull
//    private String firstName;
//
//    @NotNull
//    private String lastName;
//
//    @NotNull
//    private String pronouns;
//
//    @NotNull
//    @Email
//    private String email;
//
//    @NotNull
//    private LocalDate birthday;
//
//    @NotNull
//    private String username;
//
//    @NotNull
//    private String password;
//
//    private String status;
//
//    private boolean hasPet;
//
//    private int karma;
//
//    @ManyToMany
//    @JoinTable(
//            name = "member_karmunities",
//            joinColumns = @JoinColumn(name = "member_id"),
//            inverseJoinColumns = @JoinColumn(name = "karmunity_id")
//    )
//    private List<Karmunity> karmunities = new ArrayList<>();
//
//    @OneToMany(mappedBy = "sender")
//    private List<Invitation> sentInvitations = new ArrayList<>();
//
//    @OneToMany(mappedBy = "recipient")
//    private List<Invitation> receivedInvitations = new ArrayList<>();
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getPronouns() {
//        return pronouns;
//    }
//
//    public void setPronouns(String pronouns) {
//        this.pronouns = pronouns;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public LocalDate getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(LocalDate birthday) {
//        this.birthday = birthday;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public boolean isHasPet() {
//        return hasPet;
//    }
//
//    public void setHasPet(boolean hasPet) {
//        this.hasPet = hasPet;
//    }
//
//    public int getKarma() {
//        return karma;
//    }
//
//    public void setKarma(int karma) {
//        this.karma = karma;
//    }
//
//    public List<Karmunity> getKarmunities() {
//        return karmunities;
//    }
//
//    public void setKarmunities(List<Karmunity> karmunities) {
//        this.karmunities = karmunities;
//    }
//
//    public List<Invitation> getSentInvitations() {
//        return sentInvitations;
//    }
//
//    public void setSentInvitations(List<Invitation> sentInvitations) {
//        this.sentInvitations = sentInvitations;
//    }
//
//    public List<Invitation> getReceivedInvitations() {
//        return receivedInvitations;
//    }
//
//    public void setReceivedInvitations(List<Invitation> receivedInvitations) {
//        this.receivedInvitations = receivedInvitations;
//    }
//}