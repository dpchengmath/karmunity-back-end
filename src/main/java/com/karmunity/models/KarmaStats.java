package com.karmunity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@JsonPropertyOrder({"Id", "memberId", "karma", "ACCOUNTABILITY", "TEAMWORK", "SERVICE", "ENCOURAGEMENT",
        "INSPIRATION", "INITIATIVE", "PATIENCE", "RELIABILITY", "AUTHENTICITY", "KNOWLEDGE",
        "THOUGHTFULNESS", "GENEROSITY", "PRODUCTIVITY", "QUALITY_TIME", "OTHER"})
public class KarmaStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    private int ACCOUNTABILITY;
    private int TEAMWORK;
    private int SERVICE;
    private int ENCOURAGEMENT;
    private int INSPIRATION;
    private int INITIATIVE;
    private int PATIENCE;
    private int RELIABILITY;
    private int AUTHENTICITY;
    private int KNOWLEDGE;
    private int THOUGHTFULNESS;
    private int GENEROSITY;
    private int PRODUCTIVITY;
    private int QUALITY_TIME;
    private int OTHER;

    @Override
    public String toString() {
        return "KarmaStats{id=" + id + ", totalKarma=" + getKarma() + "}";
    }

    // Calculate the total karma points across all categories
    public int getKarma() {
        return ACCOUNTABILITY + TEAMWORK + SERVICE + ENCOURAGEMENT + INSPIRATION + INITIATIVE +
                PATIENCE + RELIABILITY + AUTHENTICITY + KNOWLEDGE + THOUGHTFULNESS + GENEROSITY +
                PRODUCTIVITY + QUALITY_TIME + OTHER;
    }

    public void addKarma(KarmaAct karmaAct, int karmaPoints) {
        switch (karmaAct) {
            case ACCOUNTABILITY:
                this.ACCOUNTABILITY += karmaPoints;
                break;
            case TEAMWORK:
                this.TEAMWORK += karmaPoints;
                break;
            case SERVICE:
                this.SERVICE += karmaPoints;
                break;
            case ENCOURAGEMENT:
                this.ENCOURAGEMENT += karmaPoints;
                break;
            case INSPIRATION:
                this.INSPIRATION += karmaPoints;
                break;
            case INITIATIVE:
                this.INITIATIVE += karmaPoints;
                break;
            case PATIENCE:
                this.PATIENCE += karmaPoints;
                break;
            case RELIABILITY:
                this.RELIABILITY += karmaPoints;
                break;
            case AUTHENTICITY:
                this.AUTHENTICITY += karmaPoints;
                break;
            case KNOWLEDGE:
                this.KNOWLEDGE += karmaPoints;
                break;
            case THOUGHTFULNESS:
                this.THOUGHTFULNESS += karmaPoints;
                break;
            case GENEROSITY:
                this.GENEROSITY += karmaPoints;
                break;
            case PRODUCTIVITY:
                this.PRODUCTIVITY += karmaPoints;
                break;
            case QUALITY_TIME:
                this.QUALITY_TIME += karmaPoints;
                break;
            case OTHER:
                this.OTHER += karmaPoints;
                break;
            default:
                throw new IllegalArgumentException("Unknown KarmaAct: " + karmaAct);
        }
    }
}
