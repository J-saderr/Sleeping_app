package com.example.demo;

import android.content.Context;

import androidx.core.content.ContextCompat;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.UUID;

public class TaskItem {
    private String name;
    private String desc;
    private LocalTime dueTime;
    private LocalDate completedDate;
    private UUID id;
    private boolean completed;

    public TaskItem(String name, String desc, LocalTime dueTime, LocalDate completedDate) {
        this.name = name;
        this.desc = desc;
        this.dueTime = dueTime;
        this.completedDate = completedDate;
        this.id = UUID.randomUUID();
        this.completed = false;

    }
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public int getImageResource() {
        // Implement logic to return the image resource ID based on the task item
        // For example:
        if (isCompleted()) {
            return R.drawable.checked;
        } else {
            return R.drawable.unchecked;
        }
    }
    public int getImageColor(Context context) {
        // Implement logic to return the color based on the task item and context
        // For example, you can return a color resource:
        if (isCompleted()) {
            return ContextCompat.getColor(context, R.color.checked);
        } else {
            return ContextCompat.getColor(context, R.color.unchecked);
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalTime dueTime) {
        this.dueTime = dueTime;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
