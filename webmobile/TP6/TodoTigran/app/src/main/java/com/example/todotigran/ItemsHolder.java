package com.example.todotigran;

import android.app.Activity;
import android.os.Build;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.example.todotigran.model.TodoItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemsHolder {

    public static final String TODO_TXT = "todo.txt";
    private final Activity activity;
    private static ItemsHolder INSTANCE;
    private List<TodoItem> items;

    public static ItemsHolder getInstance(final Activity activity) {
        if (null == INSTANCE) {
            return INSTANCE = new ItemsHolder(activity, read(activity));
        }
        return INSTANCE;
    }

    public static ItemsHolder getInstance() {
        return INSTANCE;
    }

    private ItemsHolder(final Activity activity,
                        final List<TodoItem> items) {
        this.activity = activity;
        this.items = items;
    }

    public synchronized List<TodoItem> getItems() {
        return items;
    }

    public synchronized void addItem(final TodoItem item) {
        save(item);
        this.items.add(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public synchronized void delete(final List<String> toDelete) {
        items = items.stream()
                .filter(item -> !toDelete.contains(item.getTitle()))
                .collect(Collectors.toList());
        System.out.println("removed " + toDelete);
    }

    private static List<TodoItem> read(final Activity activity) {
        final List<TodoItem> todos = new ArrayList<>();
        final File todoFile = new File(activity.getFilesDir(), TODO_TXT);
        try (final BufferedReader reader = new BufferedReader(new FileReader(todoFile))) {
            String line;
            while (null != (line = reader.readLine())) {
                System.out.println("LINE=>" + line);
                String[] item = line.split(",");
                todos.add(TodoItem.newItem(Objects.requireNonNull(item[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Toast.makeText(activity, "Read todo file", Toast.LENGTH_LONG)
                    .show();
        }

        return todos;
    }

    private void save(final TodoItem item) {
        final File todoFile = new File(activity.getFilesDir(), TODO_TXT);
        try (final FileOutputStream outputStream = new FileOutputStream(todoFile, true);
             final OutputStreamWriter writer = new OutputStreamWriter(outputStream)
        ) {
            writer.append(String.valueOf(item.getId())).append(',')
                    .append(item.getTitle()).append('\n');
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Toast.makeText(activity, "Saved todo list", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void delete(final TodoItem item) {

    }
}
