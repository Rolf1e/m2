package com.example.projettigran.domain;

import android.graphics.Color;

public enum ComparisonResult {
    RED(Color.RED),
    GREEN(Color.GREEN);
    private final int color;

    ComparisonResult(final int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
