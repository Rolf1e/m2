package com.example.testimplicite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callback(final View view) {
        startActivity(getAction(view.getId()));
    }

    private Intent getAction(final int id) {
        if (id == R.id.button_sms) {
            return openingSms();
        } else if (id == R.id.button_mms) {
            return openingMms();
        } else if (id == R.id.button_call) {
            return openingCalls();
        } else if (id == R.id.button_web) {
            return openingWeb();
        } else if (id == R.id.button_map) {
            return openingMap();
        }
        throw new IllegalStateException("Action does not exist !");
    }


    private Intent openingSms() {
        final Intent sms = createIntentRedirectToAction(Intent.ACTION_SENDTO, "sms", "0663061229");
        sms.putExtra("sms_body", "Hello World !");
        return sms;
    }

    private Intent openingMms() {
        return createIntentRedirectToAction(Intent.ACTION_SENDTO, "mms", "0663061229");
    }

    private Intent openingCalls() {
        return createIntentRedirectToAction(Intent.ACTION_DIAL, "tel", "0663061229");
    }

    private Intent openingWeb() {
        System.out.println("Opening web !");
        return createIntentWthUri(Intent.ACTION_VIEW, Uri.parse("http://www-lisic.univ-littoral.fr"));
    }

    private Intent openingMap() {
        System.out.println("Opening Map !");
        return Objects.requireNonNull(null);
    }

    private Intent createIntentRedirectToAction(final String action,
                                                final String scheme,
                                                final String to) {

        return createIntentWthUri(action, Uri.fromParts(scheme, to, null));
    }

    private Intent createIntentWthUri(final String action, final Uri uri) {
        return new Intent(action, uri);
    }
}