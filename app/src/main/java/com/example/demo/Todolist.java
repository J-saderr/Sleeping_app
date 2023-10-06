package com.example.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demo.databinding.ActivityTodolistBinding;

import java.util.List;

public class Todolist extends AppCompatActivity implements TaskItemClickListener {
    private ActivityTodolistBinding binding;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        binding = ActivityTodolistBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        ImageButton btnOutTodoList = findViewById(R.id.btnouttodolist);
        btnOutTodoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Todolist.this);
                builder.setTitle("Chọn giao diện")
                        .setMessage("Chọn:")
                        .setPositiveButton("Đánh giá giấc ngủ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Todolist.this, CLgiacngu.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Home Fragment", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Todolist.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });

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
                binding.todoListRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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