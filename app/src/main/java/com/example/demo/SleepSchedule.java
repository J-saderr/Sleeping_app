package com.example.demo;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

public class SleepSchedule {
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    public SleepSchedule() {
        // Default constructor
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }
    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getFriday() {
        return friday;
    }

    // Add methods to get the sleep data for each day
    public String getSleepDataForMonday() {
        return monday;
    }

    public String getSleepDataForTuesday() {
        return tuesday;
    }

    public String getSleepDataForWednesday() {
        return wednesday;
    }

    public String getSleepDataForThursday() {
        return thursday;
    }

    public static void main(String[] args) {

        SleepSchedule sleepSchedule = new SleepSchedule();
        sleepSchedule.setMonday("{\"bedtime\": \"22:00\", \"wake_up_time\": \"06:00\"}");
        sleepSchedule.setTuesday("{\"bedtime\": \"22:30\", \"wake_up_time\": \"06:30\"}");
        sleepSchedule.setWednesday("{\"bedtime\": \"23:00\", \"wake_up_time\": \"07:00\"}");
        sleepSchedule.setThursday("{\"bedtime\": \"23:30\", \"wake_up_time\": \"07:30\"}");
        sleepSchedule.setFriday("{\"bedtime\": \"23:45\", \"wake_up_time\": \"08:10\"}");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(sleepSchedule);

        try (Writer writer = new FileWriter("sleep_schedule.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final String JSON_FILE_PATH = "sleep_schedule.json";

    public static SleepSchedule loadSleepSchedule(Context context) {
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open(JSON_FILE_PATH);
            Reader reader = new InputStreamReader(inputStream);

            Gson gson = new GsonBuilder().create();
            return gson.fromJson(reader, SleepSchedule.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}