package com.example.projettigran.domain;

import java.util.Locale;

public class StringManipulations {

    public static String reverse(final String str) {
        return new StringBuilder(str)
                .reverse()
                .toString();
    }

    public static String cleaningUpString(final String str) {
        return str
                .toLowerCase(Locale.ROOT)
                .replaceAll("é", "e")
                .replaceAll("è", "e")
                .replaceAll("ê", "e")
                .replaceAll("ë", "e")
                .replaceAll("\\s", "")
                .replaceAll(",", "")
                .replaceAll("'", "");
    }

    private StringManipulations() {
        throw new IllegalStateException("No instance for StringManipulations");
    }
}
