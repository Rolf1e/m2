package com.example.projettigran.services.colors;

import android.util.Pair;
import android.widget.TextView;

public interface DisplayColorsService {

    int progressBarMultiplier();

    int getUntil(final int size, final long l);

    Pair<Long, Long> ruleForTimer(final int colors);

    void displayGreen(final TextView textView, final Pair<Integer, Integer> greenCoordinates);

    void displayRed(final TextView textView, Pair<Integer, Integer> redCoordinates);
}
