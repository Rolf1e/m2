package com.example.projettigran.component;

import android.content.Context;
import com.example.projettigran.component.infra.IOFileOperations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RandomPalindromeReader {

    private final IOFileOperations ioFileOperations;

    public static RandomPalindromeReader create(final IOFileOperations ioFileOperations) {
        return new RandomPalindromeReader(ioFileOperations);
    }

    private RandomPalindromeReader(final IOFileOperations ioFileOperations) {
        this.ioFileOperations = ioFileOperations;
    }

    public Optional<String> fetchRandomSentence(final Context context,
                                                final String... identifiers) {

        final List<String> sentences = Arrays.stream(identifiers)
                .map(identifier -> fetchContent(context, identifier))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return Optional.ofNullable(sentences.get(randomNumber(0, sentences.size())));
    }

    private List<String> fetchContent(final Context context,
                                      final String fileIdentifier) {
        return ioFileOperations.read(context, fileIdentifier);
    }

    private static int randomNumber(final int min,
                                    final int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
