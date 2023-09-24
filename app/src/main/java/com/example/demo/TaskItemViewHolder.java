package com.example.demo;

import android.content.Context;
import android.graphics.Paint;

import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.databinding.TaskItemCellBinding;

import java.time.format.DateTimeFormatter;

public class TaskItemViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    private final TaskItemCellBinding binding;
    private final TaskItemClickListener clickListener;
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public TaskItemViewHolder(Context context, TaskItemCellBinding binding, TaskItemClickListener clickListener) {
        super(binding.getRoot());
        this.context = context;
        this.binding = binding;
        this.clickListener = clickListener;
    }

    public void bindTaskItem(TaskItem taskItem) {
        binding.name.setText(taskItem.getName());

        if (taskItem.isCompleted()) {
            binding.name.setPaintFlags(binding.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            binding.dueTime.setPaintFlags(binding.dueTime.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            binding.name.setPaintFlags(binding.name.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            binding.dueTime.setPaintFlags(binding.dueTime.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        binding.completeButton.setImageResource(taskItem.getImageResource());
        binding.completeButton.setColorFilter(taskItem.getImageColor(context));

        binding.completeButton.setOnClickListener(v -> clickListener.completeTaskItem(taskItem));
        binding.taskCellContainer.setOnClickListener(v -> clickListener.editTaskItem(taskItem));

        if (taskItem.getDueTime() != null)
            binding.dueTime.setText(timeFormat.format(taskItem.getDueTime()));
        else
            binding.dueTime.setText("");
    }
}