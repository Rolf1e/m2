package com.example.masquestigran;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.masquestigran.infra.ActivityUtil;
import com.example.masquestigran.infra.Constants;

public class UpdateAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);

        final Intent intent = getIntent();
        ActivityUtil.setContent(findViewById(R.id.editTextStreetName), intent.getStringExtra(Constants.STREET_NAME));
        ActivityUtil.setContent(findViewById(R.id.editTextStreetNumber), intent.getStringExtra(Constants.STREET_NUMBER));
        ActivityUtil.setContent(findViewById(R.id.editPostalCode), intent.getStringExtra(Constants.POSTAL_CODE));
        ActivityUtil.setContent(findViewById(R.id.editTextCity), intent.getStringExtra(Constants.CITY));
    }

    public void validateAddress(final View view) {
        final Intent data = new Intent(this, MainActivity.class);

        data.putExtra(Constants.STREET_NAME, ActivityUtil.getContent(findViewById(R.id.editTextStreetName)));
        data.putExtra(Constants.STREET_NUMBER, ActivityUtil.getContent(findViewById(R.id.editTextStreetNumber)));
        data.putExtra(Constants.POSTAL_CODE, ActivityUtil.getContent(findViewById(R.id.editPostalCode)));
        data.putExtra(Constants.CITY, ActivityUtil.getContent(findViewById(R.id.editTextCity)));

        setResult(RESULT_OK, data);
        finish();
    }

    public void cancel(final View view) {
        setResult(RESULT_CANCELED, getIntent());
        finish();
    }
}