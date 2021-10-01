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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UPDATE_IDENTITY:
                updateIdentity(Objects.requireNonNull(data));
                break;
            default: // nothing
        }
    }

    private void updateIdentity(final Intent data) {
        ActivityUtil.setContent(findViewById(R.id.nameView), data.getStringExtra(Constants.NAME));
        ActivityUtil.setContent(findViewById(R.id.firstNameView), data.getStringExtra(Constants.FIRST_NAME));
        ActivityUtil.setContent(findViewById(R.id.phoneView), data.getStringExtra(Constants.PHONE));
    }
}