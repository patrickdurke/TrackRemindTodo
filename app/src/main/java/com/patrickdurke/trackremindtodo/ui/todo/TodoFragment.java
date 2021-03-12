package com.patrickdurke.trackremindtodo.ui.todo;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patrickdurke.trackremindtodo.R;

public class TodoFragment extends Fragment {

    private TodoViewModel todoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        todoViewModel =
                new ViewModelProvider(this).get(TodoViewModel.class);
        View root = inflater.inflate(R.layout.todo_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_todo);
        todoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}