package com.example.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.demo.databinding.ActivityMainBinding;

import java.util.List;

public class Todolist extends AppCompatActivity implements TaskItemClickListener {
    private ActivityMainBinding binding;
    private TaskViewModel taskViewModel;
    private Button newTaskButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        binding.newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NewTaskSheet(null).show(getSupportFragmentManager(), "newTaskTag");
            }
        });

        setRecyclerView();
    }

    private void setRecyclerView() {
        final Todolist todolist = this;
        taskViewModel.getTaskItems().observe(this, new Observer<List<TaskItem>>() {
            @Override
            public void onChanged(List<TaskItem> taskItems) {
                binding.todoListRecyclerView.setLayoutManager(new LinearLayoutManager(todolist));
                binding.todoListRecyclerView.setAdapter(new TaskItemAdapter(taskItems, todolist));
            }
        });
    }

    public void editTaskItem(TaskItem taskItem) {
        new NewTaskSheet(taskItem).show(getSupportFragmentManager(), "newTaskTag");
    }

    public void completeTaskItem(TaskItem taskItem) {
        taskViewModel.setCompleted(taskItem);
    }
}