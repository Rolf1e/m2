package com.example.projettigran.services.colors;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Pair;
import android.widget.TextView;
import com.example.projettigran.component.activities.Activities;

public class AdminDisplayColorsService implements DisplayColorsService {

    private static final long ONE_SECOND = 1000;

    public static DisplayColorsService create() {
        return new AdminDisplayColorsService();
    }

    private AdminDisplayColorsService() {
    }

    @Override
    public int progressBarMultiplier() {
        return 1;
    }

    @Override
    public int getUntil(final int size,
                        final long l) {
        return Math.toIntExact((size * ONE_SECOND - l) / ONE_SECOND) + 1;
    }

    @Override
    public Pair<Long, Long> ruleForTimer(final int colors) {
        return Pair.create(colors * ONE_SECOND, ONE_SECOND);
    }

    @Override
    public void displayGreen(final TextView textView,
                             final Pair<Integer, Integer> greenCoordinates) {

        final Spannable word = SpannableString.valueOf(Activities.extractText(textView));
        word.setSpan(new BackgroundColorSpan(Color.GREEN), greenCoordinates.first, greenCoordinates.second, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(word);
    }

    @Override
    public void displayRed(final TextView textView,
                           final Pair<Integer, Integer> redCoordinates) {

        final Spannable word = SpannableString.valueOf(Activities.extractText(textView));
        word.setSpan(new BackgroundColorSpan(Color.GREEN), 0, redCoordinates.first, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new BackgroundColorSpan(Color.RED), redCoordinates.first, redCoordinates.second, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(word);
    }

}
