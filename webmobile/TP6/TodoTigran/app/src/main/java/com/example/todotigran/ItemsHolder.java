package com.example.todotigran;

import com.example.todotigran.model.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class ItemsHolder {

    private static ItemsHolder INSTANCE;
    private final List<TodoItem> items;

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
}
