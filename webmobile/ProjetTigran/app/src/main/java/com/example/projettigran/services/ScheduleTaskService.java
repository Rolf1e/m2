package com.example.projettigran.services;

import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.projettigran.domain.ComparisonResult;
import com.example.projettigran.domain.PalindromeTimer;

import java.util.List;

public class ScheduleTaskService {

    private final DisplayColorsService displayColorsService;

    public static ScheduleTaskService create(final DisplayColorsService displayColorsService) {
        return new ScheduleTaskService(displayColorsService);
    }

    private ScheduleTaskService(final DisplayColorsService displayColorsService) {
        this.displayColorsService = displayColorsService;
    }

    public CountDownTimer schedule(final List<ComparisonResult> colorsToApply,
                                   final TextView palindromeView,
                                   final TextView reversePalindromeView,
                                   final ProgressBar progressBar) {

        return PalindromeTimer.create(colorsToApply, palindromeView, reversePalindromeView, displayColorsService, progressBar)
                .start();
    }

}
