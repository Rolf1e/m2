package com.example.todotigran;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.todotigran.model.TodoItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadList() {
        final ListView listView = findViewById(R.id.todoList);
        final List<String> titles = ItemsHolder.getInstance(this)
                .getItems()
                .stream()
                .map(TodoItem::getTitle)
                .collect(Collectors.toList());

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, titles));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addTodo(final View view) {
        final Intent intent = new Intent(this, AddTodoActivity.class);

        startActivityForResult(intent, 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void delete(final View view) {
        final ListView listView = findViewById(R.id.todoList);
        ItemsHolder.getInstance()
                .delete(getSelectedItems(listView));

        recreate();
    }

    private List<String> getSelectedItems(final ListView listView) {
        final SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();

        if (0 == checkedItemPositions.size()) {
            return Collections.emptyList();
        }

        final List<String> selected = new ArrayList<>();
        final ListAdapter adapter = listView.getAdapter();
        final int size = adapter.getCount();

        for (int i = 0; i < size; i++) {
            final int key = checkedItemPositions.keyAt(i);
            if (checkedItemPositions.get(key)) {
                selected.add((String) adapter.getItem(key));
            }
        }

        return selected;
    }


}