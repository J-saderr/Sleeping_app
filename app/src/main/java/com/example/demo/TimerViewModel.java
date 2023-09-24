package com.example.demo;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerViewModel extends ViewModel {
    private Timer timer;
    private int secondsElapsed = 0;
    private long startTimeInMillis;
    private String bedtime;
    private long stopTimeInMillis;

    public TimerViewModel() {
    }

    public void startTimer() {
        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                // Xử lý thời gian hiện tại (ví dụ: in ra log)
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date resultDate = new Date(currentTimeMillis);
                String formattedTime = sdf.format(resultDate);
                Log.d("CurrentTime", "Current time: " + currentTimeMillis);
                // Xử lý thời gian (ví dụ: tăng giây lên 1)
                secondsElapsed++;
            }
        }, 0, 1000);
    }
    public void setStartTimeInMillis(long startTimeInMillis) {
        this.startTimeInMillis = startTimeInMillis;
    }
    public long getStartTimeInMillis() {
        return startTimeInMillis;
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date resultDate = new Date(currentTimeMillis);
            String formattedTime = sdf.format(resultDate);
            Log.d("CurrentTime", "Current time: " + currentTimeMillis);
            setBedtime(formattedTime);
            setStopTimeInMillis(currentTimeMillis);
        }
    }

    public String getBedtime() {
        return bedtime;
    }

    public void setBedtime(String bedtime) {
        this.bedtime = bedtime;
    }

    public long getStopTimeInMillis() {
        return stopTimeInMillis;
    }

    public void setStopTimeInMillis(long stopTimeInMillis) {
        this.stopTimeInMillis = stopTimeInMillis;
    }

    public int getSecondsElapsed() {
        return secondsElapsed;
    }
    public String toJson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static TimerViewModel fromJson(String json) {
        return new GsonBuilder().create().fromJson(json, TimerViewModel.class);
    }
}