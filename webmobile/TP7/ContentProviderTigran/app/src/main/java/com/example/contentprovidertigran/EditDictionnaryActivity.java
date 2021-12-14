package com.example.contentprovidertigran;

import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditDictionnaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dictionnary);
    }

    public void push(final View view) {
        final EditText editText = findViewById(R.id.input_text);
        final String value = editText.getText().toString();
        DictionnaryManager.create(this, UserDictionary.Words.CONTENT_URI)
                .insert(UserDictionary.Words.WORD, value);
        finish();
    }
}