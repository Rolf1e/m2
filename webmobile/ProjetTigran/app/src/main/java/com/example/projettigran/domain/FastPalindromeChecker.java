package com.example.projettigran.domain;

public class FastPalindromeChecker {

    public static boolean checkIsPalindrome(final String sentence) {
        final String cleaned = StringManipulations.cleaningUpString(sentence);
        final String reversed = StringManipulations.reverse(cleaned);
        final int length = cleaned.length();
        if (length != reversed.length()) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            final ComparisonResult result = CharacterComparator.compare(cleaned.charAt(i), reversed.charAt(i));
            if (ComparisonResult.RED.equals(result)) {
                return false;
            }
        }

        return true;
    }

    private FastPalindromeChecker() {
        throw new IllegalStateException("No FastPalindromeChecker instance");
    }

}
