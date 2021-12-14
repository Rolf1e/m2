package com.example.contentprovidertigran;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDictionnaryActivity extends AppCompatActivity {

    private final List<String> selected = new ArrayList<>();

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
        final ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            final Cursor atPosition = (Cursor) listView.getItemAtPosition(i);
            selected.add(atPosition.getString(0));
        });
        DictionnaryManager.create(this, UserDictionary.Words.CONTENT_URI)
                .display(
                        QUERY_PROJECTION,
                        "(locale IS NULL) or (locale=?)",
                        new String[]{Locale.getDefault().toString()},
                        order,
                        listView
                );

    }

    public void add(final View view) {
        final Intent intent = new Intent(this, AddDictionnaryActivity.class);
        intent.putExtra("Dico", UserDictionary.Words.CONTENT_URI.toString());
        startActivityForResult(intent, 2);
    }

    public void delete(final View view) {
        final DictionnaryManager dictionnaryManager = DictionnaryManager.create(this, UserDictionary.Words.CONTENT_URI);
        for (final String s : selected) {
            dictionnaryManager.delete(UserDictionary.Words.WORD + " = ?", new String[]{s});
        }
        loadDictionnay();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recreate();
    }

}
