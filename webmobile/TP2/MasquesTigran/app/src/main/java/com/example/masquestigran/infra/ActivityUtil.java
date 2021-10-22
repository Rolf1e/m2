package com.example.masquestigran.infra;

import android.widget.TextView;

import java.util.Objects;

public class ActivityUtil {

    public static String getContent(final TextView textView) {
        return Objects.requireNonNull(textView.getText())
                .toString();
    }

    public static void setContent(final TextView view,
                                  final String content) {
        view.setText(content);
    }

    private ActivityUtil() {
        // hide this
    }
}
