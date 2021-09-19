package com.example.cacltigran.domain.calculator;

import android.os.Build;
import android.text.Editable;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Optional;

public class AndroidCalculatorService {

    public static AndroidCalculatorService create() {
        return new AndroidCalculatorService();
    }

    public void raz(final EditText firstInputBox,
                    final EditText secondInputBox,
                    final TextView resultBox) {

        firstInputBox.setText("");
        secondInputBox.setText("");
        resultBox.setText("Calculatrice remise à zéro !");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void computeAndUpdateView(final AppCompatActivity activity,
                                     final EditText firstInputBox,
                                     final EditText secondInputBox,
                                     final TextView resultBox,
                                     final RadioGroup operationsSelector) {

        try {
            final Calculator from = Calculator.from(extractOperation(operationsSelector, activity));

            final Optional<Integer> first = extractInteger(firstInputBox);
            final Optional<Integer> second = extractInteger(secondInputBox);

            if (first.isPresent() && second.isPresent()) {
                final Number result = from.compute(first.get(), second.get());
                resultBox.setText("Résultat = " + result);
            } else {
                resultBox.setText("Une des entrées est invalide !");
            }

        } catch (NullPointerException npe) {
            resultBox.setText("Merci de choisir une opération !");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static Optional<Integer> extractInteger(final EditText editText) {
        final Editable text = editText.getText();

        try {
            return Optional.of(Integer.valueOf(text.toString()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
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
