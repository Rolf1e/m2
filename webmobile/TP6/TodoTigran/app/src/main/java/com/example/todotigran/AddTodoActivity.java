package com.example.todotigran;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.todotigran.model.TodoItem;

public class AddTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
    }

    public void cancel(final View view) {
        finish();
    }

    public void validate(final View view) {
        final ItemsHolder holder = ItemsHolder.getInstance();
        final TextView inputText = findViewById(R.id.inputText);

        final String text = inputText.getText()
                .toString();

        holder.addItem(TodoItem.newItem(text));
        finish();
    }
}