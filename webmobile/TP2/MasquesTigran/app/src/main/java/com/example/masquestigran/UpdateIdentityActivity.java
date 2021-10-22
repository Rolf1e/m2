package com.example.masquestigran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.masquestigran.infra.ActivityUtil;
import com.example.masquestigran.infra.Constants;

public class UpdateIdentityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_identity);

        final Intent intent = getIntent();
        ActivityUtil.setContent(findViewById(R.id.editTextName), intent.getStringExtra(Constants.NAME));
        ActivityUtil.setContent(findViewById(R.id.editTextFirstName), intent.getStringExtra(Constants.FIRST_NAME));
        ActivityUtil.setContent(findViewById(R.id.editTextPhone), intent.getStringExtra(Constants.PHONE));
    }

    public void validateIdentity(final View view) {
        final Intent data = new Intent(this, MainActivity.class);

        data.putExtra(Constants.NAME, ActivityUtil.getContent(findViewById(R.id.editTextName)));
        data.putExtra(Constants.FIRST_NAME, ActivityUtil.getContent(findViewById(R.id.editTextFirstName)));
        data.putExtra(Constants.PHONE, ActivityUtil.getContent(findViewById(R.id.editTextPhone)));

        setResult(RESULT_OK, data);
        finish();
    }

    public void cancel(final View view) {
        setResult(RESULT_CANCELED, getIntent());
        finish();
    }
}