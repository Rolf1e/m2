package com.example.cacltigran;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cacltigran.domain.service.CalculatorService;
import com.example.cacltigran.domain.service.CalculatorServiceImpl;

public class MainActivity extends AppCompatActivity {

    private final CalculatorService calculatorService;

    public MainActivity() {
        this.calculatorService = CalculatorServiceImpl.create();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void compute(final View view) {
        view.getId();
        System.out.println("Result = ");
    }

}