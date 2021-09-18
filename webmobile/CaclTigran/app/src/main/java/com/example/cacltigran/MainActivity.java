package com.example.cacltigran;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cacltigran.domain.calulator.Calculator;
import com.example.cacltigran.domain.calulator.CalculatorService;
import com.example.cacltigran.domain.calulator.CalculatorServiceImpl;

import java.util.Objects;

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
        final EditText firstInputBox = findViewById(R.id.first_input_box);
        final EditText secondInputBox = findViewById(R.id.second_input_box);

        final RadioGroup operations = findViewById(R.id.operations_buttons);

        final Number result = calculatorService.compute(
                extractInteger(firstInputBox),
                extractInteger(secondInputBox),
                Calculator.from(extractOperation(operations, this))
        );

        final TextView resultBox = findViewById(R.id.result_box);
        final String resultText = "Résultat = " + result;
        resultBox.setText(resultText);
    }

    private static int extractInteger(final EditText editText) {
        final Editable text = editText.getText();
        return Integer.parseInt(text.toString());
    }

    private static String extractOperation(final RadioGroup radioGroup,
                                           final AppCompatActivity activity) {

        final int choosedOperation = radioGroup.getCheckedRadioButtonId();
        final RadioButton button = activity.findViewById(choosedOperation);
        return Objects.requireNonNull(button.getText())
                .toString();
    }

}