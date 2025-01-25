//package com.karmunity.models;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//public class Karma {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "giver_id", nullable = false)
//    private Member giver;
//
//    @ManyToOne
//    @JoinColumn(name = "receiver_id", nullable = false)
//    private Member receiver;
//
//import java.time.LocalDateTime;
//
//@Column(nullable = false)
//private int karmaPoints;
//
//@Column(nullable = false)
//private String karmaAct;
//
//@Column(nullable = false)
//private LocalDateTime sentAt;
//
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
//    public Member getGiver() {
//        return giver;
//    }
//
//    public void setGiver(Member giver) {
//        this.giver = giver;
//    }
//
//    public Member getReceiver() {
//        return receiver;
//    }
//
//    public void setReceiver(Member receiver) {
//        this.receiver = receiver;
//    }
//
//    public int getKarmaPoints() {
//        return karmaPoints;
//    }
//
//    public void setKarmaPoints(int karmaPoints) {
//        this.karmaPoints = karmaPoints;
//    }
//
//    public String getKarmaAct() {
//        return karmaAct;
//    }
//
//    public void KarmaAct(String karmaAct) {
//        this.karmaAct = karmaAct;
//    }
//
//    public LocalDateTime getSentAt() {
//        return sentAt;
//    }
//
//    public void setSentAt(LocalDateTime sentAt) {
//        this.sentAt = sentAt;
//    }
//}