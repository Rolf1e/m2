package com.example.contentprovidertigran.user;

import android.content.ContentValues;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.contentprovidertigran.R;
import com.example.contentprovidertigran.infra.DictionnaryManager;

public class AddDictionnaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dictionnary);
    }

    public void push(final View view) {
        final EditText editText = findViewById(R.id.input_text);
        final String value = editText.getText().toString();

        final DictionnaryManager dictionnaryManager = DictionnaryManager.create(this, UserDictionary.Words.CONTENT_URI);

        final ContentValues contentValues = new ContentValues();
        contentValues.put(UserDictionary.Words.WORD, value);
        dictionnaryManager.insert(contentValues);

        finish();
    }
}