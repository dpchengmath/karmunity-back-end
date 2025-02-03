package com.karmunity.models;

import lombok.Getter;

@Getter
public enum KarmaAct {
    ACCOUNTABILITY(1),
    TEAMWORK(1),
    SERVICE(1),
    ENCOURAGEMENT(1),
    INSPIRATION(1),
    INITIATIVE(1),
    PATIENCE(1),
    RELIABILITY(1),
    AUTHENTICITY(1),
    KNOWLEDGE(1),
    THOGHTFULNESS(1),
    GENEROSITY(1),
    PRODUCTIVITY(1),
    OTHER(1);

    private final int points;

    KarmaAct(int points) {
        this.points = points;
    }

    public static KarmaAct fromString(String value) {
        for (KarmaAct act : KarmaAct.values()) {
            if (act.name().equalsIgnoreCase(value)) {
                return act;
            }
        }
        return OTHER;
    }
}