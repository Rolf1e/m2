package com.example.testintent01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Intent intent = getIntent();
        final String text = intent.getStringExtra(Constants.TEXT);

        final TextView viewById = findViewById(R.id.textView);
        viewById.setText(text);
    }
}