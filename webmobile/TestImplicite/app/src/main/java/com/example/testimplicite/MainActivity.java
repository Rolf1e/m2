package com.example.testimplicite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openingSms(final View view) {
        final Intent sms = createIntentRedirectToAction(Intent.ACTION_SENDTO, "sms", "0663061229");
        sms.putExtra("sms_body", "Hello World !");

        startActivity(sms);
    }

    public void openingMms(final View view) {
        startActivity(createIntentRedirectToAction(Intent.ACTION_SENDTO, "mms", "0663061229"));
    }

    public void openingCalls(final View view) {
        startActivity(createIntentRedirectToAction(Intent.ACTION_DIAL, "tel", "0663061229"));
    }

    public void openingWeb(final View view) {
        System.out.println("Opening web !");
    }

    public void openingMap(final View view) {
        System.out.println("Opening Map !");
    }

    private Intent createIntentRedirectToAction(final String action,
                                                final String scheme,
                                                final String to) {
        return new Intent(action, Uri.fromParts(scheme, to, null));
    }
}