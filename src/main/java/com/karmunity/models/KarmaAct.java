package com.karmunity.models;

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
    THOUGHTFULNESS(1),
    GENEROSITY(1),
    PRODUCTIVITY(1),
    QUALITY_TIME(1),
    OTHER(1);

    private final int points;

    KarmaAct(int points) {
        this.points = points;
    }

    public static KarmaAct fromString(String karmaActStr) {
        if (karmaActStr == null) {
            throw new IllegalArgumentException("Invalid Karma Act: null");
        }
        karmaActStr = karmaActStr.trim();
        for (KarmaAct karmaAct : KarmaAct.values()) {
            if (karmaAct.name().equalsIgnoreCase(karmaActStr)) {
                return karmaAct;
            }
        }
        throw new IllegalArgumentException("Invalid Karma Act: " + karmaActStr);
    }
}