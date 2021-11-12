package com.example.todotigran;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.todotigran.databinding.FragmentFirstBinding;
import com.example.todotigran.model.TodoItem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private final List<TodoItem> items;

    public FirstFragment() {
        this.items = Arrays.asList(
                TodoItem.newItem("item 1"),
                TodoItem.newItem("item 2"),
                TodoItem.newItem("item 3")
        );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        final NavController navController = Navigation.findNavController(container);

        binding.fab.setOnClickListener(view -> navController.navigate(R.id.SecondFragment));

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final List<String> titles = items.stream()
                .map(TodoItem::getTitle)
                .collect(Collectors.toList());


        binding.todoList.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_checked, titles));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}