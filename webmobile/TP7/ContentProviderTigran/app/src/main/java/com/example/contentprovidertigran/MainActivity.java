package com.example.contentprovidertigran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.contentprovidertigran.contacts.UserContactActivity;
import com.example.contentprovidertigran.user.UserDictionnaryActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dictionnary(final View view) {
        startActivity(new Intent(this, UserDictionnaryActivity.class));
    }

    public void contacts(final View view) {
        startActivity(new Intent(this, UserContactActivity.class));
    }
}
