package com.example.projettigran.services.colors;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Pair;
import android.widget.TextView;
import com.example.projettigran.component.activities.Activities;

public class TestDisplayColorsService implements DisplayColorsService {

    private static final long ONE_SECOND = 1000;

    public static DisplayColorsService create() {
        return new TestDisplayColorsService();
    }

    private TestDisplayColorsService() {
    }

    @Override
    public int progressBarMultiplier() {
        return 2;
    }

    @Override
    public int getUntil(int size, long l) {
        return Math.toIntExact((size * ONE_SECOND - l) /  ONE_SECOND) + 1;
    }

    @Override
    public Pair<Long, Long> ruleForTimer(int colors) {
        return Pair.create(colors / 2 * ONE_SECOND, ONE_SECOND);
    }

    @Override
    public void displayGreen(final TextView textView,
                             final Pair<Integer, Integer> greenCoordinates) {

        final Spannable word = SpannableString.valueOf(Activities.extractText(textView));
        final BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.GREEN);
        word.setSpan(backgroundColorSpan, 0, greenCoordinates.second, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        final int length = word.length();
        final int left = length - (greenCoordinates.second - greenCoordinates.first);
        word.setSpan(new BackgroundColorSpan(Color.BLUE), left, length, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(word);
    }

    @Override
    public void displayRed(final TextView textView,
                           final Pair<Integer, Integer> redCoordinates) {

        final Spannable word = SpannableString.valueOf(Activities.extractText(textView));
        word.setSpan(new BackgroundColorSpan(Color.GREEN), 0, redCoordinates.first, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        final int length = word.length();
        final int left = length - (redCoordinates.second - redCoordinates.first);
        word.setSpan(new BackgroundColorSpan(Color.BLUE), left, length, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new BackgroundColorSpan(Color.RED), redCoordinates.first, redCoordinates.second, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(word);
    }
}
