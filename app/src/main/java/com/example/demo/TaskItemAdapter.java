package com.example.demo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.databinding.TaskItemCellBinding;

import java.util.List;

public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemViewHolder> {
    private final List<TaskItem> taskItems;
    private final TaskItemClickListener clickListener;

    public TaskItemAdapter(List<TaskItem> taskItems, TaskItemClickListener clickListener) {
        this.taskItems = taskItems;
        this.clickListener = clickListener;
    }

    @Override
    public TaskItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TaskItemCellBinding binding = TaskItemCellBinding.inflate(inflater, parent, false);
        return new TaskItemViewHolder(parent.getContext(), binding, clickListener);
    }

    @Override
    public void onBindViewHolder(TaskItemViewHolder holder, int position) {
        holder.bindTaskItem(taskItems.get(position));
    }

    @Override
    public int getItemCount() {
        return taskItems.size();
    }
}