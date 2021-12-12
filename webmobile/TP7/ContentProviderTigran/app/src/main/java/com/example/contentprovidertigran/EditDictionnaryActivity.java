package com.example.contentprovidertigran;

import android.content.ContentValues;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditDictionnaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dictionnary);
    }

    public void push(final View view) {
        final EditText editText = findViewById(R.id.input_text);
        final ContentValues contentValues = new ContentValues();
        final String value = editText.getText().toString();
        contentValues.put(UserDictionary.Words.WORD, value);
        getContentResolver()
                .insert(UserDictionary.Words.CONTENT_URI, contentValues);
        Toast.makeText(this, "Insert " + value, Toast.LENGTH_LONG)
                .show();
        finish();
    }
}