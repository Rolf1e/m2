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

    public void callback(final View view) {
        handleAction(view.getId());
    }

    private void handleAction(final int id) {
        if (id == R.id.button_sms || id == R.id.button_mms || id == R.id.button_call) {
            final Intent intent = new Intent(this, PhoneActivity.class);
            intent.putExtra("CallerType", phoningAction(id).name());
            startActivity(intent);
        } else if (id == R.id.button_web) {
            startActivity(openingWeb());
        } else if (id == R.id.button_map) {
            startActivity(openingMap());
        }
        throw new IllegalStateException("Action does not exist !");
    }

    private static PhoneActivity.Type phoningAction(final int id) {
        if (id == R.id.button_sms) {
            return PhoneActivity.Type.SMS;
        } else if (id == R.id.button_mms) {
            return PhoneActivity.Type.MMS;
        } else if (id == R.id.button_call) {
            return PhoneActivity.Type.CALL;
        }
        throw new IllegalStateException("Action does not exist !");
    }

    private Intent openingWeb() {
        return Utils.createIntentWthUri(Intent.ACTION_VIEW, Uri.parse("http://www-lisic.univ-littoral.fr"));
    }

    private Intent openingMap() {
        final Intent map = Utils.createIntentWthUri(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+CA+94043"));
        map.setPackage("com.google.android.apps.maps");
        return map;
    }

}