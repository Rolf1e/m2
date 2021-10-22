package com.example.testintent01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(final View view) {
        final EditText editText = findViewById(R.id.textInput);
        final String text = Objects.requireNonNull(editText.getText())
                .toString();

        final Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra(Constants.TEXT, text);

        startActivity(intent);

        System.out.println(text);
    }
}