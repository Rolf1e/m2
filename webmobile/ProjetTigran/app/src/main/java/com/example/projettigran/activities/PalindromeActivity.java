package com.example.projettigran.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projettigran.R;
import com.example.projettigran.domain.StringManipulations;
import com.example.projettigran.services.DisplayColorsService;
import com.example.projettigran.services.ScheduleTaskService;

public class PalindromeActivity extends AppCompatActivity {

    private final ScheduleTaskService scheduleTaskService;

    public PalindromeActivity() {
        super();
        this.scheduleTaskService = ScheduleTaskService.create(DisplayColorsService.create());
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palindrome);
    }


    // Buttons

    public void clean(final View view) {
        final EditText enterPalindrome = findViewById(R.id.enter_palindrome_editText);
        final String text = StringManipulations.cleaningUpString(Activities.extractText(enterPalindrome));

        final TextView palindromeView = findViewById(R.id.palindrome_textView);
        palindromeView.setText(text);
    }

    public void reverse(final View view) {
        final TextView palindromeView = findViewById(R.id.palindrome_textView);
        final String extractText = Activities.extractText(palindromeView);
        if (extractText.isEmpty()) {
            Toast.makeText(this, "You need to clean first !", Toast.LENGTH_SHORT)
                    .show();
        } else {
            final TextView reversePalindromeView = findViewById(R.id.palindrome_reverse_textView);
            reversePalindromeView.setText(StringManipulations.reverse(extractText));
        }

    }

    public void compare(final View view) {
        final TextView palindromeView = findViewById(R.id.palindrome_textView);
        final String normalText = Activities.extractText(palindromeView);

        final TextView reversePalindromeView = findViewById(R.id.palindrome_reverse_textView);
        final String reversedText = Activities.extractText(reversePalindromeView);

        final int normalTextSize = normalText.length();
        if (normalTextSize != reversedText.length()) {
            Toast.makeText(this, "Texts should be the same size", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        scheduleTaskService.schedule(normalText, reversedText, palindromeView, reversePalindromeView);
    }

}