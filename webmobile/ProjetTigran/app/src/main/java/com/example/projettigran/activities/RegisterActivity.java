package com.example.projettigran.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projettigran.R;
import com.example.projettigran.component.PalindromeWriter;
import com.example.projettigran.component.infra.IOFileOperations;
import com.example.projettigran.component.infra.ResourceHandler;

public class RegisterActivity extends AppCompatActivity {

    private final IOFileOperations ioFileOperations;

    public RegisterActivity() {
        this.ioFileOperations = IOFileOperations.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void showFileSentence(final View view) {
        Activities.redirect(this, ShowFileSentenceActivity.class, 1);
    }

    public void add_palindrome(final View view) {
        final EditText sentenceInput = findViewById(R.id.sentence_input);
        final String sentence = Activities.extractText(sentenceInput);
        addContentToEndOfFile(PalindromeActivity.PALINDROME_FILE, sentence);
    }

    public void add_sentence(final View view) {
        final EditText sentenceInput = findViewById(R.id.sentence_input);
        final String sentence = Activities.extractText(sentenceInput);
        addContentToEndOfFile(PalindromeActivity.NON_PALINDROME_FILE, sentence);
    }

    private void addContentToEndOfFile(final String destinationFile,
                                       final String sentence) {

        ioFileOperations.save(this, destinationFile, sentence, true);
    }
}