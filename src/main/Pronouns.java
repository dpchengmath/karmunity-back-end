public enum Pronouns {
    he_him("he/him"),
    she_her("she/her"),
    they_them("they/them"),
    she_they("she/they"),
    he_they("he/they"),
    other("other");

    private final String displayName;

    Pronouns(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Pronouns fromString(String text) {
        // Normalize input to replace slashes with underscores
        String normalizedText = text.replace("/", "_");

        // Match normalized input with the enum constants
        for (Pronouns p : Pronouns.values()) {
            if (p.displayName.equalsIgnoreCase(normalizedText)) {
                return p;
            }
        }

        // Return 'other' if no match is found
        return Pronouns.other;
    }
}