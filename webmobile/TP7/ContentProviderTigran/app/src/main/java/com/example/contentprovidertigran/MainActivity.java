package com.example.contentprovidertigran;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                UserDictionary.Words._ID,
                UserDictionary.Words.WORD
        };

        final Cursor cursor = getContentResolver()
                .query(UserDictionary.Words.CONTENT_URI,
                        QUERY_PROJECTION,
                        "(locale IS NULL) or (locale=?)",
                        new String[]{Locale.getDefault().toString()},
                        order
                );

        final SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                cursor,
                QUERY_PROJECTION,
                new int[]{
                        android.R.id.text1,
                        android.R.id.text2
                },
                0
        );
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(simpleCursorAdapter);
    }

}