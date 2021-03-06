package com.example.masquestigran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.masquestigran.infra.ActivityUtil;
import com.example.masquestigran.infra.Constants;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final int UPDATE_IDENTITY = 2;
    private static final int UPDATE_ADDRESS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void updateIdentity(final View view) {
        final Intent intent = new Intent(this, UpdateIdentityActivity.class);

        intent.putExtra(Constants.NAME, ActivityUtil.getContent(findViewById(R.id.nameView)));
        intent.putExtra(Constants.FIRST_NAME, ActivityUtil.getContent(findViewById(R.id.firstNameView)));
        intent.putExtra(Constants.PHONE, ActivityUtil.getContent(findViewById(R.id.phoneView)));

        startActivityForResult(intent, UPDATE_IDENTITY);
    }

    public void updateAddress(final View view) {
        final Intent intent = new Intent(this, UpdateAddressActivity.class);

        intent.putExtra(Constants.STREET_NAME, ActivityUtil.getContent(findViewById(R.id.streetNumberView)));
        intent.putExtra(Constants.STREET_NUMBER, ActivityUtil.getContent(findViewById(R.id.streetNameView)));
        intent.putExtra(Constants.POSTAL_CODE, ActivityUtil.getContent(findViewById(R.id.postalCodeView)));
        intent.putExtra(Constants.CITY, ActivityUtil.getContent(findViewById(R.id.cityView)));

        startActivityForResult(intent, UPDATE_ADDRESS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case UPDATE_IDENTITY:
                updateIdentity(Objects.requireNonNull(data));
                break;
            case UPDATE_ADDRESS:
                updateAddress(Objects.requireNonNull(data));
                break;
            default: // nothing
        }
    }

    private void updateAddress(final Intent data) {
        ActivityUtil.setContent(findViewById(R.id.streetNameView), data.getStringExtra(Constants.STREET_NAME));
        ActivityUtil.setContent(findViewById(R.id.streetNumberView), data.getStringExtra(Constants.STREET_NUMBER));
        ActivityUtil.setContent(findViewById(R.id.postalCodeView), data.getStringExtra(Constants.POSTAL_CODE));
        ActivityUtil.setContent(findViewById(R.id.cityView), data.getStringExtra(Constants.CITY));
    }

    private void updateIdentity(final Intent data) {
        ActivityUtil.setContent(findViewById(R.id.nameView), data.getStringExtra(Constants.NAME));
        ActivityUtil.setContent(findViewById(R.id.firstNameView), data.getStringExtra(Constants.FIRST_NAME));
        ActivityUtil.setContent(findViewById(R.id.phoneView), data.getStringExtra(Constants.PHONE));
    }
}