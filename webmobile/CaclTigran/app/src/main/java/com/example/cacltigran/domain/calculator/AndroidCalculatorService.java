package com.example.cacltigran.domain.calculator;

import android.text.Editable;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AndroidCalculatorService {

    public static AndroidCalculatorService create() {
        return new AndroidCalculatorService();
    }

    public void computeAndUpdateView(final AppCompatActivity activity,
                                     final EditText firstInputBox,
                                     final EditText secondInputBox,
                                     final TextView resultBox,
                                     final RadioGroup operationsSelector) {


        final Calculator from = Calculator.from(extractOperation(operationsSelector, activity));
        final Number result = from.compute(
                extractInteger(firstInputBox),
                extractInteger(secondInputBox)
        );
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

    private AndroidCalculatorService() {
    }
}
