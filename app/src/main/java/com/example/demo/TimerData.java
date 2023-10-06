package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TimerData {
    private String selectedOption1;
    private String selectedOption2;
    private String selectedOption3;
    private String selectedOption4;
    private String selectedOptionG1;
    private String selectedOptionG2;
    private String selectedOptionG3;
    private String selectedOptionG4;
    private String selectedOptionG5;
    private long time1;
    private long time2;
    private long startTime;
    private long stopTime;

    public TimerData() {
        // Default constructor
    }


    public String getSelectedOption1() {
        return selectedOption1;
    }

    public void setSelectedOption1(String selectedOption1) {
        this.selectedOption1 = selectedOption1;
    }
    public String getSelectedOption2() {
        return selectedOption2;
    }

    public void setSelectedOption2(String selectedOption2) {
        this.selectedOption2 = selectedOption2;
    }
    public String getSelectedOption3() {
        return selectedOption3;
    }

    public void setSelectedOption3(String selectedOption3) {
        this.selectedOption3 = selectedOption3;
    }
    public String getSelectedOption4() {
        return selectedOption4;
    }

    public void setSelectedOption4(String selectedOption4) {
        this.selectedOption4 = selectedOption4;
    }
    public String getSelectedOptionG1() {
        return selectedOptionG1;
    }
    public void setSelectedOptionG1(String selectedOptionG1) {
        this.selectedOptionG1 = selectedOptionG1;
    }
    public String getSelectedOptionG2() {
        return selectedOptionG2;
    }
    public void setSelectedOptionG2(String selectedOptionG2) {
        this.selectedOptionG2 = selectedOptionG2;
    }
    public String getSelectedOptionG3() {
        return selectedOptionG3;
    }
    public void setSelectedOptionG3(String selectedOptionG3) {
        this.selectedOptionG3 = selectedOptionG3;
    }
    public String getSelectedOptionG4() {
        return selectedOptionG4;
    }
    public void setSelectedOptionG4(String selectedOptionG4) {
        this.selectedOptionG4 = selectedOptionG4;
    }
    public String getSelectedOptionG5() {
        return selectedOptionG5;
    }
    public void setSelectedOptionG5(String selectedOptionG5) {
        this.selectedOptionG5 = selectedOptionG5;
    }

    public long getTime1() {
        return time1;
    }

    public void setTime1(long time1) {
        this.time1 = time1;
    }

    public long getTime2() {
        return time2;
    }

    public void setTime2(long time2) {
        this.time2 = time2;
    }


    public TimerData(long startTime, long stopTime) {
        this.startTime = startTime;
        this.stopTime = stopTime;
    }
    public void setStart(long startTime) {
        this.startTime = startTime;
    }

    public long getStart() {
        return startTime;
    }

    public void setStop(long stopTime) {
        this.stopTime = stopTime;}
    public long getStop() {
        return stopTime;
    }

    public void saveToJson(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TimerData loadFromJson(String filePath) {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(filePath);
            return gson.fromJson(reader, TimerData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
