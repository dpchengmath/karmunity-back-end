package com.karmunity.dto;

import com.karmunity.models.KarmaStats;
import lombok.Getter;

@Getter
public class KarmaStatsDTO {
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

    public KarmaStatsDTO(KarmaStats karmaStats) {
        this.ACCOUNTABILITY = karmaStats != null ? karmaStats.getACCOUNTABILITY() : 0;
        this.TEAMWORK = karmaStats != null ? karmaStats.getTEAMWORK() : 0;
        this.SERVICE = karmaStats != null ? karmaStats.getSERVICE() : 0;
        this.ENCOURAGEMENT = karmaStats != null ? karmaStats.getENCOURAGEMENT() : 0;
        this.INSPIRATION = karmaStats != null ? karmaStats.getINSPIRATION() : 0;
        this.INITIATIVE = karmaStats != null ? karmaStats.getINITIATIVE() : 0;
        this.PATIENCE = karmaStats != null ? karmaStats.getPATIENCE() : 0;
        this.RELIABILITY = karmaStats != null ? karmaStats.getRELIABILITY() : 0;
        this.AUTHENTICITY = karmaStats != null ? karmaStats.getAUTHENTICITY() : 0;
        this.KNOWLEDGE = karmaStats != null ? karmaStats.getKNOWLEDGE() : 0;
        this.THOUGHTFULNESS = karmaStats != null ? karmaStats.getTHOUGHTFULNESS() : 0;
        this.GENEROSITY = karmaStats != null ? karmaStats.getGENEROSITY() : 0;
        this.PRODUCTIVITY = karmaStats != null ? karmaStats.getPRODUCTIVITY() : 0;
        this.QUALITY_TIME = karmaStats != null ? karmaStats.getQUALITY_TIME() : 0;
        this.OTHER = karmaStats != null ? karmaStats.getOTHER() : 0;
    }

    public KarmaStatsDTO() {
        this.ACCOUNTABILITY = 0;
        this.TEAMWORK = 0;
        this.SERVICE = 0;
        this.ENCOURAGEMENT = 0;
        this.INSPIRATION = 0;
        this.INITIATIVE = 0;
        this.PATIENCE = 0;
        this.RELIABILITY = 0;
        this.AUTHENTICITY = 0;
        this.KNOWLEDGE = 0;
        this.THOUGHTFULNESS = 0;
        this.GENEROSITY = 0;
        this.PRODUCTIVITY = 0;
        this.QUALITY_TIME = 0;
        this.OTHER = 0;
    }
}