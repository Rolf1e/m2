package com.example.projettigran.domain;

import android.os.CountDownTimer;
import android.util.Pair;
import android.widget.TextView;
import com.example.projettigran.services.DisplayColorsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PalindromeTimer extends CountDownTimer {

    private static final long ONE_SECOND = 1000;

    private final List<ComparisonResult> colorsToApply;
    private final TextView palindromeView;
    private final TextView reversePalindromeView;
    private final DisplayColorsService displayColorsService;

    public static CountDownTimer create(final List<ComparisonResult> colorsToApply,
                                        final TextView palindromeView,
                                        final TextView reversePalindromeView,
                                        final DisplayColorsService displayColorsService) {

        return new PalindromeTimer(colorsToApply, palindromeView, reversePalindromeView, displayColorsService);
    }

    private PalindromeTimer(final List<ComparisonResult> colorsToApply,
                            final TextView palindromeView,
                            final TextView reversePalindromeView,
                            final DisplayColorsService displayColorsService) {

        super(colorsToApply.size() * ONE_SECOND, ONE_SECOND);
        this.colorsToApply = colorsToApply;
        this.palindromeView = palindromeView;
        this.reversePalindromeView = reversePalindromeView;
        this.displayColorsService = displayColorsService;
    }

    @Override
    public void onTick(final long l) {
        // We want to apply the color on the correct letters
        final int until = Math.toIntExact((colorsToApply.size() * ONE_SECOND - l) / ONE_SECOND) + 1;

        final List<ComparisonResult> colorsToBeAppliedAtTime = IntStream.range(0, until)
                .boxed()
                .map(colorsToApply::get)
                .collect(Collectors.toList());

        final int size = colorsToBeAppliedAtTime.size();
        if (colorsToBeAppliedAtTime.contains(ComparisonResult.RED)) {
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
