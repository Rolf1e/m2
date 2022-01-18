package com.example.projettigran.activities;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projettigran.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void palindrome(final View view) {
        final Intent intent = new Intent(this, PalindromeActivity.class);
        startActivityForResult(intent, 1);
    }


}