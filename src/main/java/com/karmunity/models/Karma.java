//package com.karmunity.models;
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
//    private Member giver;  // The member who is giving the karma
//
//    @ManyToOne
//    @JoinColumn(name = "receiver_id", nullable = false)
//    private Member receiver;  // The member who is receiving the karma
//
//    @Column(nullable = false)
//    private int points;  // Karma points awarded to the receiver
//
//    @Column(nullable = false)
//    private String reason;  // Reason for karma award (e.g., "Good deed", "Karmunity contribution")
//
//    @Column(nullable = false)
//    private LocalDateTime createdAt;  // When the karma was awarded
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
//    public int getPoints() {
//        return points;
//    }
//
//    public void setPoints(int points) {
//        this.points = points;
//    }
//
//    public String getReason() {
//        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}