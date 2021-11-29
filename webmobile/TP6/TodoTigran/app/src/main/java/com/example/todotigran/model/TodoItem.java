package com.example.todotigran.model;

public class TodoItem {

    private static int COUNT = 0;

    private final int id;
    private final String title;
    private boolean selected;

    public static synchronized TodoItem newItem(final String title) {
        return new TodoItem(COUNT++, title);
    }

    private TodoItem(final int id,
                     final String title) {
        this.id = id;
        this.title = title;
        this.selected = false;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void toggleSelect() {
        selected = !selected;
    }
}
