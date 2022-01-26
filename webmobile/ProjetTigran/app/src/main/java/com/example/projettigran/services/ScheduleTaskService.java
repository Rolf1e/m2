package com.example.projettigran.services;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.projettigran.domain.ComparisonResult;
import com.example.projettigran.domain.timer.PalindromeTimer;
import com.example.projettigran.services.colors.DisplayColorsService;

import java.util.Collection;
import java.util.List;

public class ScheduleTaskService {

    private final DisplayColorsService displayColorsService;

    public static ScheduleTaskService create(final DisplayColorsService displayColorsService) {
        return new ScheduleTaskService(displayColorsService);
    }

    private ScheduleTaskService(final DisplayColorsService displayColorsService) {
        this.displayColorsService = displayColorsService;
    }

    public CountDownTimer schedule(final Context context,
                                   final List<ComparisonResult> colorsToApply,
                                   final Collection<TextView> textViews,
                                   final ProgressBar progressBar) {

        return PalindromeTimer.create(context, colorsToApply, textViews, displayColorsService, progressBar)
                .start();
    }

}
