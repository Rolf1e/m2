package com.example.projettigran.domain.timer;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Pair;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.projettigran.domain.ComparisonResult;
import com.example.projettigran.services.colors.DisplayColorsService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PalindromeTimer extends CountDownTimer {

    private final Context context;
    private final List<ComparisonResult> colorsToApply;
    private final Collection<TextView> textViews;
    private final DisplayColorsService displayColorsService;
    private final ProgressBar progressBar;

    public static CountDownTimer create(final Context context,
                                        final List<ComparisonResult> colorsToApply,
                                        final Collection<TextView> textViews,
                                        final DisplayColorsService displayColorsService,
                                        final ProgressBar progressBar) {

        return new PalindromeTimer(context, colorsToApply, textViews, displayColorsService, progressBar);
    }

    private PalindromeTimer(final Context context,
                            final List<ComparisonResult> colorsToApply,
                            final Collection<TextView> textViews,
                            final DisplayColorsService displayColorsService,
                            final ProgressBar progressBar) {

        super(
                displayColorsService.ruleForTimer(colorsToApply.size()).first,
                displayColorsService.ruleForTimer(colorsToApply.size()).second
        );
        this.context = context;
        this.colorsToApply = colorsToApply;
        this.textViews = textViews;
        this.displayColorsService = displayColorsService;
        this.progressBar = progressBar;
    }

    @Override
    public void onTick(final long l) {
        // We want to apply the color on the correct letters
        final int until = displayColorsService.getUntil(colorsToApply.size(), l);
        System.out.println("until:" + until);

        progressBar.setProgress(until * displayColorsService.progressBarMultiplier(), true);
        final List<ComparisonResult> colorsToBeAppliedAtTime = IntStream.range(0, until)
                .boxed()
                .map(colorsToApply::get)
                .collect(Collectors.toList());

        final int size = colorsToBeAppliedAtTime.size();
        if (colorsToBeAppliedAtTime.contains(ComparisonResult.RED)) {
            final Pair<Integer, Integer> redCoordinates = Pair.create(size - 1, size);
            textViews.forEach(textView -> displayColorsService.displayRed(textView, redCoordinates));
            cancel();
        } else {
            final Pair<Integer, Integer> coordinates = Pair.create(0, until);
            textViews.forEach(textView -> displayColorsService.displayGreen(textView, coordinates));
        }

    }

    @Override
    public void onFinish() {
        Toast.makeText(context, "Comparaison is over", Toast.LENGTH_SHORT)
                .show();
    }
}
