package com.example.projettigran.activities;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projettigran.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void palindrome(final View view) {
        Activities.redirect(this, PalindromeActivity.class, 1);
    }

    public void register(final View view) {
        Activities.redirect(this, RegisterActivity.class, 1);
    }


}