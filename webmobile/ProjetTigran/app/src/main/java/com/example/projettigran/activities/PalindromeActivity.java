package com.example.projettigran.activities;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projettigran.R;
import com.example.projettigran.component.PopUpWindowFactory;
import com.example.projettigran.component.RandomPalindromeLoader;
import com.example.projettigran.component.ResourceLoader;
import com.example.projettigran.domain.StringManipulations;
import com.example.projettigran.services.DisplayColorsService;
import com.example.projettigran.services.ScheduleTaskService;

public class PalindromeActivity extends AppCompatActivity {

    private final ScheduleTaskService scheduleTaskService;
    private final PopUpWindowFactory windowFactory;
    private RandomPalindromeLoader randomPalindromeLoader;

    public PalindromeActivity() {
        super();
        this.scheduleTaskService = ScheduleTaskService.create(DisplayColorsService.create());
        this.windowFactory = PopUpWindowFactory.create();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palindrome);
        final ResourceLoader resourceLoader = ResourceLoader.from(this);
        this.randomPalindromeLoader = RandomPalindromeLoader.create(resourceLoader);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.palindrome_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        final int itemId = item.getItemId();
        if (R.id.question_palindrome == itemId) {
            final LayoutInflater layoutInflater = LayoutInflater.from(this);
            final View customView = layoutInflater.inflate(R.layout.popup, null);
            final LinearLayout location = findViewById(R.id.linear_layout_palindrome);
            final PopupWindow popUp = windowFactory.manufactor(location, customView);

            System.out.println("HELLO");
        } else if (R.id.item_random_palindrome == itemId) {
            loadRandomSentenceFromFile(R.raw.palindrome);
        } else if (R.id.item_random_sentence == itemId) {
            loadRandomSentenceFromFile(R.raw.palindrome, R.raw.non_palindrome);
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadRandomSentenceFromFile(final Object... identifiers) {
        final EditText enterPalindrome = findViewById(R.id.enter_palindrome_editText);
        randomPalindromeLoader.fetchRandomSentence(this, identifiers)
                .ifPresent(enterPalindrome::setText);
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