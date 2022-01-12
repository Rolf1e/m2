package com.example.projettigran.domain;

public class CharacterComparator {

    public static ComparisonResult compare(final char a, final char b) {
        return a == b ? ComparisonResult.GREEN : ComparisonResult.RED;
    }

    private CharacterComparator() {
        throw new IllegalStateException("No instance for CharacterComparator");
    }

}
