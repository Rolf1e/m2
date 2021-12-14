package com.example.contentprovidertigran;

import android.content.Intent;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.Switch;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class UserDictionnaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dictionnary);

        loadDictionnay();
    }

    public void reloadDictionnary(final View view) {
        loadDictionnay();
    }

    public void loadDictionnay() {
        final Switch orderSwitch = findViewById(R.id.order_switch);

        final String order = orderSwitch.isChecked()
                ? UserDictionary.Words.WORD + " DESC"
                : UserDictionary.Words.WORD + " ASC";

        final String[] QUERY_PROJECTION = {
                UserDictionary.Words.WORD,
                UserDictionary.Words._ID
        };
        DictionnaryManager.create(this, UserDictionary.Words.CONTENT_URI)
                .display(
                        QUERY_PROJECTION,
                        "(locale IS NULL) or (locale=?)",
                        new String[]{Locale.getDefault().toString()},
                        order,
                        findViewById(R.id.listView)
                );

    }

    public void add(final View view) {
        final Intent intent = new Intent(this, EditDictionnaryActivity.class);
        startActivityForResult(intent, 2);
    }

    public void delete(final View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recreate();
    }

}
