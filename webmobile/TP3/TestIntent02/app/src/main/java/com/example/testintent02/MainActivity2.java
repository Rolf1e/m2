package com.example.testintent02;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Intent intent = getIntent();
        final String text = intent.getStringExtra(Constants.TEXT);
        final EditText editText = findViewById(R.id.editTextTextPersonName);
        editText.setText(text);
    }

    public void validate(final View view) {
        final EditText editText = findViewById(R.id.editTextTextPersonName);
        final String text = Objects.requireNonNull(editText.getText())
                .toString();
        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.TEXT_UPDATED, text);

        setResult(RESULT_OK, intent);
        finish();
    }

}