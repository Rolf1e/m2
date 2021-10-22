package com.example.testimplicite;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openingSms(final View view) {
        final Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("sms", "0663061229", null));
        sms.putExtra("sms_body", "Hello World !");
        startActivity(sms);
    }

    public void openingMms(final View view) {
        System.out.println("Opening MMS !");
    }

    public void openingCalls(final View view) {
        System.out.println("Opening calls !");
    }

    public void openingWeb(final View view) {
        System.out.println("Opening web !");
    }

    public void openingMap(final View view) {
        System.out.println("Opening Map !");
    }
}