package com.example.projettigran.component;

import android.content.Context;
import android.widget.Toast;
import com.example.projettigran.ReadRawFileException;

import java.util.*;
import java.util.stream.Collectors;

public class RandomPalindromeLoader {
    private final ResourceLoader resourceLoader;

    public static RandomPalindromeLoader create(final ResourceLoader resourceLoader) {
        return new RandomPalindromeLoader(resourceLoader);
    }

    private RandomPalindromeLoader(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Optional<String> fetchRandomSentence(final Context context,
                                                final Object... identifiers) {

        final List<String> sentences = Arrays.stream(identifiers)
                .map(identifier -> fetchContent(context, (int) identifier))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return Optional.ofNullable(sentences.get(randomNumber(0, sentences.size())));
    }

    private List<String> fetchContent(final Context context,
                                      final int fileIdentifier) {
        try {
            return resourceLoader.loadFileFromRawFile(fileIdentifier);
        } catch (ReadRawFileException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG)
                    .show();
            return Collections.emptyList();
        }
    }

    private static int randomNumber(final int min,
                                    final int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
