package com.example.projettigran.domain;

import android.os.CountDownTimer;
import android.util.Pair;
import android.widget.TextView;
import com.example.projettigran.services.DisplayColorsService;

import java.util.ArrayList;
import java.util.List;

public class PalindromeTimer extends CountDownTimer {

    private static final long ONE_SECOND = 1000;

    private final String word1;
    private final String word2;
    private final TextView palindromeView;
    private final TextView reversePalindromeView;
    private final DisplayColorsService displayColorsService;


    public static CountDownTimer create(final String word1,
                                        final String word2,
                                        final TextView palindromeView,
                                        final TextView reversePalindromeView,
                                        final DisplayColorsService displayColorsService) {

        return new PalindromeTimer(word1, word2, palindromeView, reversePalindromeView, displayColorsService);
    }


    private PalindromeTimer(final String word1,
                            final String word2,
                            final TextView palindromeView,
                            final TextView reversePalindromeView,
                            final DisplayColorsService displayColorsService) {

        super(word1.length() * ONE_SECOND, ONE_SECOND);
        this.word1 = word1;
        this.word2 = word2;
        this.palindromeView = palindromeView;
        this.reversePalindromeView = reversePalindromeView;
        this.displayColorsService = displayColorsService;
    }

    @Override
    public void onTick(final long l) {
        final int until = Math.toIntExact((word1.length() * ONE_SECOND - l) / ONE_SECOND) + 1;
        final List<ComparisonResult> colorsToApply = new ArrayList<>(until);
        for (int i = 0; i < until; i++) {
            final ComparisonResult compare = CharacterComparator.compare(word1.charAt(i), word2.charAt(i));
            colorsToApply.add(compare);
            if (ComparisonResult.RED.equals(compare)) {
                break;
            }
        }

        final int size = colorsToApply.size();
        if (colorsToApply.contains(ComparisonResult.RED)) {
            displayColorsService.displayRed(palindromeView, Pair.create(size - 1, size));
            displayColorsService.displayRed(reversePalindromeView, Pair.create(size - 1, size));
            cancel();
        } else {
            displayColorsService.displayGreen(palindromeView, Pair.create(0, until));
            displayColorsService.displayGreen(reversePalindromeView, Pair.create(0, until));
        }

    }

    @Override
    public void onFinish() {

    }
}
