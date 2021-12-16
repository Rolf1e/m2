package com.example.contentprovidertigran.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.contentprovidertigran.R;
import com.example.contentprovidertigran.infra.DictionnaryManager;

import java.util.ArrayList;
import java.util.List;

public class UserContactActivity extends AppCompatActivity {

    private List<String> selected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contact);
        loadDictionnay();
    }

    public void loadDictionnay() {
        final String[] QUERY_PROJECTION = {
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.Data._ID
        };
        final ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            final Cursor atPosition = (Cursor) listView.getItemAtPosition(i);
            selected.add(atPosition.getString(0));
        });
        DictionnaryManager.create(this, ContactsContract.Data.CONTENT_URI)
                .display(
                        QUERY_PROJECTION,
                        null,
                        null,
                        null,
                        listView
                );

    }

    public void add(final View view) {
        final Intent intent = new Intent(this, AddContactActivity.class);
        startActivityForResult(intent, 2);
    }

    public void delete(final View view) {
        final DictionnaryManager dictionnaryManager = DictionnaryManager.create(this, ContactsContract.Data.CONTENT_URI);
        for (final String s : selected) {
            dictionnaryManager.delete(ContactsContract.Data.DISPLAY_NAME + " = ?", new String[]{s});
        }
        loadDictionnay();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recreate();
    }


}