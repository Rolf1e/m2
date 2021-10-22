package com.example.testimplicite;

import android.content.Intent;
import android.net.Uri;

public class Utils {
    public static Intent createIntentRedirectToAction(final String action,
                                                       final String scheme,
                                                       final String to) {

        return createIntentWthUri(action, Uri.fromParts(scheme, to, null));
    }

    public static Intent createIntentWthUri(final String action, final Uri uri) {
        return new Intent(action, uri);

    }

    private Utils() {
        // Hide this
    }
}
