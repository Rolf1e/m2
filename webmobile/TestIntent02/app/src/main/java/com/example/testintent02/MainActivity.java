package com.example.testintent02;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void update(final View view) {
        final TextView textView = findViewById(R.id.textView2);
        final Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra(Constants.TEXT, textView.getText().toString());

        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final String text = Objects.requireNonNull(data).getStringExtra(Constants.TEXT);
        if (null != text) {
            final TextView textView = findViewById(R.id.textView2);
            textView.setText(text);
        }
    }
}