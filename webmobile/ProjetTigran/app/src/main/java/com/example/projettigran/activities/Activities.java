package com.example.projettigran.activities;

import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class Activities {

    public static String extractText(final EditText editText) {
        return Objects.requireNonNull(editText.getText())
                .toString();
    }

    public static String extractText(final TextView textView) {
        return Objects.requireNonNull(textView.getText())
                .toString();
    }

    private Activities() {
        throw new IllegalStateException("No instance of Activities");
    }
}
