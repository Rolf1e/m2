package com.example.todotigran.model;

public class TodoItem {
    private final String title;
    private final boolean done;

    public static TodoItem newItem(final String title) {
        return new TodoItem(title, false);
    }

    private TodoItem(final String title,
                     final boolean done) {
        this.title = title;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }
}
