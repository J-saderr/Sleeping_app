package com.example.demo;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.demo.databinding.FragmentNewTaskSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.time.LocalTime;

public class NewTaskSheet extends BottomSheetDialogFragment {

    private FragmentNewTaskSheetBinding binding;
    private TaskViewModel taskViewModel;
    private TaskItem taskItem;
    private LocalTime dueTime;

    public NewTaskSheet(TaskItem taskItem) {
        this.taskItem = taskItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentActivity activity = requireActivity();

        if (taskItem != null) {
            binding.taskTitle.setText("Edit Task");
            Editable.Factory factory = Editable.Factory.getInstance();
            binding.name.setText(factory.newEditable(taskItem.getName()));
            binding.desc.setText(factory.newEditable(taskItem.getDesc()));
            if (taskItem.getDueTime() != null) {
                dueTime = taskItem.getDueTime();
                updateTimeButtonText();
            }
        } else {
            binding.taskTitle.setText("New Task");
        }

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAction();
            }
        });

        binding.timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePicker();
            }
        });
    }

    private void openTimePicker() {
        if (dueTime == null) {
            dueTime = LocalTime.now();
        }
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                dueTime = LocalTime.of(selectedHour, selectedMinute);
                updateTimeButtonText();
            }
        };

        TimePickerDialog dialog = new TimePickerDialog(requireActivity(), listener, dueTime.getHour(), dueTime.getMinute(), true);
        dialog.setTitle("Task Due");
        dialog.show();
    }
    private void updateTimeButtonText() {
        Button timePickerButton;
        binding.timePickerButton.setText(String.format("%02d:%02d", dueTime.getHour(), dueTime.getMinute()));
    }

    private void saveAction() {
        String name = binding.name.getText().toString();
        String desc = binding.desc.getText().toString();

        if (taskItem == null) {
            TaskItem newTask = new TaskItem(name, desc, dueTime, null);
            taskViewModel.addTaskItem(newTask);
        } else {
            taskViewModel.updateTaskItem(taskItem.getId(), name, desc, dueTime);
        }

        binding.name.setText("");
        binding.desc.setText("");
        dismiss();
    }
}