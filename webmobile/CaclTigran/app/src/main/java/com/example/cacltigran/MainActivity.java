package com.example.cacltigran;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cacltigran.domain.calculator.AndroidCalculatorService;

public class MainActivity extends AppCompatActivity {

    private final AndroidCalculatorService calculatorService;

    public MainActivity() {
        this.calculatorService = AndroidCalculatorService.create();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void compute(final View view) {
        calculatorService.computeAndUpdateView(
                this,
                findViewById(R.id.first_input_box),
                findViewById(R.id.second_input_box),
                findViewById(R.id.result_box),
                findViewById(R.id.operations_buttons)
        );
    }


}