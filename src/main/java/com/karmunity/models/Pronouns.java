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

    // Method to map a string to the corresponding enum constant
    public static Pronouns fromString(String text) {
        // Normalize the input text by replacing any slashes with underscores
        String normalizedText = text.replaceAll("/", "_").toUpperCase();

        for (Pronouns pronoun : Pronouns.values()) {
            if (pronoun.name().equals(normalizedText)) {
                return pronoun;
            }
        }
        return OTHER;  // Default to 'OTHER' if no match is found
    }
}