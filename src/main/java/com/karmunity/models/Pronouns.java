package com.karmunity.models;

import lombok.Getter;

@Getter
public enum Pronouns {
    HE_HIM("he/him"),
    SHE_HER("she/her"),
    THEY_THEM("they/them"),
    SHE_THEY("she/they"),
    HE_THEY("he/they"),
    OTHER("other");

    private final String displayName;

    Pronouns(String displayName) {
        this.displayName = displayName;
    }

    public static Pronouns fromString(String text) {
        for (Pronouns p : Pronouns.values()) {
            if (p.displayName.equalsIgnoreCase(text)) {
                return p;
            }
        }
        return null;
    }
}