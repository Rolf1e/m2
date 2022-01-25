package com.example.projettigran.component.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class Activities {

    public static void redirect(final Activity from,
                                final Class<?> to,
                                final int requestCode) {
        final Intent intent = new Intent(from, to);
        from.startActivityForResult(intent, requestCode);
    }

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
