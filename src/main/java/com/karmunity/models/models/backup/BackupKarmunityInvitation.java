//package com.karmunity.models;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import java.time.LocalDateTime;
//
//@Entity
//public class KarmunityInvitation {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "sender_id", nullable = false)
//    private Member sender;
//
//    @ManyToOne
//    @JoinColumn(name = "recipient_id", nullable = false)
//    private Member recipient;
//
//    @ManyToOne
//    @JoinColumn(name = "karmunity_id", nullable = false)
//    private Karmunity karmunity;
//
//    @NotNull
//    private LocalDateTime sentAt;
//
//    @Enumerated(EnumType.STRING)
//    @NotNull
//    private KarmunityInvitationStatus status;
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
//    public Member getSender() {
//        return sender;
//    }
//
//    public void setSender(Member sender) {
//        this.sender = sender;
//    }
//
//    public Member getRecipient() {
//        return recipient;
//    }
//
//    public void setRecipient(Member recipient) {
//        this.recipient = recipient;
//    }
//
//    public Karmunity getKarmunity() {
//        return karmunity;
//    }
//
//    public void setKarmunity(Karmunity karmunity) {
//        this.karmunity = karmunity;
//    }
//
//    public LocalDateTime getSentAt() {
//        return sentAt;
//    }
//
//    public void setSentAt(LocalDateTime sentAt) {
//        this.sentAt = sentAt;
//    }
//
//    public KarmunityInvitationStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(KarmunityInvitationStatus status) {
//        this.status = status;
//    }
//}
//
//enum KarmunityInvitationStatus {
//    PENDING, ACCEPTED, REJECTED
//}