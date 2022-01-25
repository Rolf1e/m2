package com.example.projettigran.services;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Pair;
import android.widget.TextView;
import com.example.projettigran.component.activities.Activities;

public class DisplayColorsService {

    public static DisplayColorsService create() {
        return new DisplayColorsService();
    }

    private DisplayColorsService() {
    }

    public void displayGreen(final TextView textView,
                             final Pair<Integer, Integer> greenCoordinates) {

        final Spannable word = SpannableString.valueOf(Activities.extractText(textView));
        word.setSpan(new BackgroundColorSpan(Color.GREEN), greenCoordinates.first, greenCoordinates.second, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(word);
    }

    public void displayRed(final TextView textView,
                           final Pair<Integer, Integer> redCoordinates) {

        final Spannable word = SpannableString.valueOf(Activities.extractText(textView));
        word.setSpan(new BackgroundColorSpan(Color.GREEN), 0, redCoordinates.first, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        word.setSpan(new BackgroundColorSpan(Color.RED), redCoordinates.first, redCoordinates.second, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(word);
    }
}
