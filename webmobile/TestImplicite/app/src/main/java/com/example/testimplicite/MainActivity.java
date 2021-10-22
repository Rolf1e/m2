package com.example.testimplicite;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openingSms(final View view) {
        System.out.println("Opening SMS !");
    }

    public void openingMms(final View view) {
        System.out.println("Opening MMS !");
    }

    public void openingCalls(final View view) {
        System.out.println("Opening calls !");
    }

    public void openingWeb(final View view) {
        System.out.println("Opening web !");
    }

    public void openingMap(final View view) {
        System.out.println("Opening Map !");
    }
}