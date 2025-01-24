//package com.karmunity.models;
//
//@Entity
//public class Shoutout {
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
//    @JoinColumn(name = "receiver_id", nullable = false)
//    private Member receiver;
//
//    @Column(nullable = false)
//    private String content;
//
//    @Column(nullable = false)
//    private LocalDateTime createdAt;  // When the shoutout was created
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private NotificationStatus notificationStatus;  // Status of the notification
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
//    public Member getReceiver() {
//        return receiver;
//    }
//
//    public void setReceiver(Member receiver) {
//        this.receiver = receiver;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public NotificationStatus getNotificationStatus() {
//        return notificationStatus;
//    }
//
//    public void setNotificationStatus(NotificationStatus notificationStatus) {
//        this.notificationStatus = notificationStatus;
//    }
//}