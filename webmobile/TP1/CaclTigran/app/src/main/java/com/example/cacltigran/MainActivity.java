package com.example.cacltigran;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.RequiresApi;
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

    public void raz(final View view) {
        calculatorService.raz(
                findViewById(R.id.first_input_box),
                findViewById(R.id.second_input_box),
                findViewById(R.id.result_box)
        );
    }

    public void quit(final View view) {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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