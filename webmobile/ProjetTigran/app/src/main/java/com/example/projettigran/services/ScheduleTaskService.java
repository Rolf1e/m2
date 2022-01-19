package com.example.projettigran.services;

import android.os.CountDownTimer;
import android.widget.TextView;
import com.example.projettigran.domain.PalindromeTimer;

public class ScheduleTaskService {

    private final DisplayColorsService displayColorsService;

    public static ScheduleTaskService create(final DisplayColorsService displayColorsService) {
        return new ScheduleTaskService(displayColorsService);
    }

    private ScheduleTaskService(final DisplayColorsService displayColorsService) {
        this.displayColorsService = displayColorsService;
    }

    public CountDownTimer schedule(final String word1,
                                   final String word2,
                                   final TextView palindromeView,
                                   final TextView reversePalindromeView) {

        return PalindromeTimer.create(word1, word2, palindromeView, reversePalindromeView, displayColorsService)
                .start();
    }
}
