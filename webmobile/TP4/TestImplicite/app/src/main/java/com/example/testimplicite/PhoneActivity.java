package com.example.testimplicite;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class PhoneActivity extends AppCompatActivity {

    private Type type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        final Intent intent = getIntent();
        final String callerType = intent.getStringExtra("CallerType");
        this.type = Type.from(callerType);
    }

    public enum Type {
        SMS,
        MMS,
        CALL;

        public static Type from(final String name) {
            switch (name) {
                case "SMS":
                    return SMS;
                case "MMS":
                    return MMS;
                case "CALL":
                    return CALL;
            }
            throw new IllegalStateException("Bad phoning action");
        }

    }

    public void dial(final View view) {
        final EditText input = findViewById(R.id.input_phone_number);
        final String number = input.getText().toString();
        startActivity(getAction(number));
    }

    private Intent getAction(final String number) {
        switch (Objects.requireNonNull(type)) {
            case SMS:
                return openingSms(number);
            case MMS:
                return openingMms(number);
            case CALL:
                return openingCalls(number);
        }
        throw new IllegalStateException("Bad action ");
    }

    private Intent openingSms(final String number) {
        final Intent sms = Utils.createIntentRedirectToAction(Intent.ACTION_SENDTO, "sms", number);
        sms.putExtra("sms_body", "Hello World !");
        return sms;
    }

    private Intent openingMms(final String number) {
        return Utils.createIntentRedirectToAction(Intent.ACTION_SENDTO, "mms", number);
    }

    private Intent openingCalls(final String number) {
        return Utils.createIntentRedirectToAction(Intent.ACTION_DIAL, "tel", number);
    }
}