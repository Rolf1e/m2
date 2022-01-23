package com.example.projettigran.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projettigran.R;
import com.example.projettigran.component.infra.IOFileOperations;

import java.util.List;

public class ShowFileSentenceActivity extends AppCompatActivity {

    private final IOFileOperations ioFileOperations;

    public ShowFileSentenceActivity() {
        this.ioFileOperations = IOFileOperations.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_file_sentence);
    }

    public void showPalindromes(final View view) {
        showFile(PalindromeActivity.PALINDROME_FILE);
    }

    public void showSentences(final View view) {
        showFile(PalindromeActivity.NON_PALINDROME_FILE);
    }

    private void showFile(final String fileName) {
        final List<String> sentences = ioFileOperations.read(this, fileName);
        final ListView listView = findViewById(R.id.sentences_list_view);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sentences));
    }
}