package com.example.projettigran;

public class ReadRawFileException extends Exception {
    public ReadRawFileException(final int identifier) {
        super("Failed to read file with identifier: " + identifier + " in raw directory");
    }
}
