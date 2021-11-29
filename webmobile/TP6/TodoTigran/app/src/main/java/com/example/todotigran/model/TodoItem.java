package com.example.todotigran.model;

public class TodoItem {

    private static int COUNT = 0;

    private final int id;
    private final String title;

    public static synchronized TodoItem newItem(final String title) {
        return new TodoItem(COUNT++, title);
    }

    private TodoItem(final int id,
                     final String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
