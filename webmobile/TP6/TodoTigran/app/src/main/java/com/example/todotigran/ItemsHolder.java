package com.example.todotigran;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.todotigran.model.TodoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemsHolder {

    private static ItemsHolder INSTANCE;
    private List<TodoItem> items;

    public static ItemsHolder getInstance() {
        if (null == INSTANCE) {
            return INSTANCE = new ItemsHolder(new ArrayList<>());
        }
        return INSTANCE;
    }

    private ItemsHolder(final List<TodoItem> items) {
        this.items = items;
    }

    public synchronized List<TodoItem> getItems() {
        return items;
    }

    public synchronized void addItem(final TodoItem item) {
        this.items.add(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public synchronized void delete(final List<String> toDelete) {
        items = items.stream()
                .filter(item -> !toDelete.contains(item.getTitle()))
                .collect(Collectors.toList());
        System.out.println("removed " + toDelete);
    }
}
