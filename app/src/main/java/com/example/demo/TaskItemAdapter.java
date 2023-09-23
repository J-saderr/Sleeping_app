package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.demo.databinding.TaskItemCellBinding;

import java.util.List;

public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemViewHolder> {
    private List<TaskItem> taskItems;
    private TaskItemClickListener clickListener;
    private Context context;

    public TaskItemAdapter(List<TaskItem> taskItems, TaskItemClickListener clickListener) {
        this.taskItems = taskItems;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public TaskItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        TaskItemCellBinding binding = TaskItemCellBinding.inflate(inflater, parent, false);
        return new TaskItemViewHolder(context, binding, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskItemViewHolder holder, int position) {
        holder.bindTaskItem(taskItems.get(position));
    }

    public int getItemCount() {
        return taskItems != null ? taskItems.size() : 0;
    }
}