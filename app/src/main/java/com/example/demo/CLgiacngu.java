package com.example.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CLgiacngu extends AppCompatActivity {
    public CLgiacngu() {
    }
    private TimerData timerData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clgiacngu);
        ImageButton myImageButton = findViewById(R.id.myImageButton);
        String filePath = getFilesDir() + "/data.json";
        timerData = TimerData.loadFromJson(filePath);

        initializeUI();

        myImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CLgiacngu.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button btnTodolistButton;
        btnTodolistButton = findViewById(R.id.btntodolist);
        btnTodolistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CLgiacngu.this, Todolist.class);
                startActivity(intent);
            }
        });
    }
    private void initializeUI() {
        String filePath = getFilesDir() + "/data.json";

        if (timerData != null) {

            TextView selectedOptionTextView1 = findViewById(R.id.wq1);
            selectedOptionTextView1.setText(timerData.getSelectedOption1());

            TextView selectedOptionTextView2 = findViewById(R.id.wq2);
            selectedOptionTextView2.setText(timerData.getSelectedOption2());

            TextView selectedOptionTextView3 = findViewById(R.id.wq3);
            selectedOptionTextView3.setText(timerData.getSelectedOption3());

            TextView selectedOptionTextView4 = findViewById(R.id.wq4);
            selectedOptionTextView4.setText(timerData.getSelectedOption4());

            TextView selectedOptionG1 = findViewById(R.id.wg1);
            selectedOptionG1.setText(timerData.getSelectedOptionG1());

            TextView selectedOptionG2 = findViewById(R.id.wg2);
            selectedOptionG2.setText(timerData.getSelectedOptionG2());

            TextView selectedOptionG3 = findViewById(R.id.wg3);
            selectedOptionG3.setText(timerData.getSelectedOptionG3());

            TextView selectedOptionG4 = findViewById(R.id.wg4);
            selectedOptionG4.setText(timerData.getSelectedOptionG4());

            TextView selectedOptionG5 = findViewById(R.id.wg5);
            selectedOptionG5.setText(timerData.getSelectedOptionG5());

        }

        String[] options3 = new String[10];
        options3[0] = "1 lần";
        for (int i = 2; i <= 10; i++) {
            options3[i - 1] = i + " lần";
        }

        List<String> timeList1 = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            for (int minute = 0; minute < 60; minute += 30) {
                String hourString = String.format("%02d", hour);
                String minuteString = String.format("%02d", minute);
                String time = hourString + ":" + minuteString;
                timeList1.add(time);
            }
        }

        String[] options1 = timeList1.toArray(new String[0]);
        for (String time : options1) {
            System.out.println(time);
        }
        List<String> timeList2 = new ArrayList<>();
        for (int hour = 0; hour < 2; hour++) {
            for (int minute = 0; minute < 60; minute += 5) {
                if (hour >0) {
                    String hourString = String.valueOf(hour);
                    String minuteString = String.valueOf(minute);
                    String time = hourString + " tiếng " + minuteString + " phút";
                    timeList2.add(time);}
                if (hour == 0) {
                    String minuteString = String.valueOf(minute);
                    String time = minuteString + " phút";
                    timeList2.add(time);}
            }
        }
        String[] options2 = timeList2.toArray(new String[0]);

        for (String time : options2) {
            System.out.println(time);
        }

        List<String> timeList4 = new ArrayList<>();
        for (int hour = 0; hour < 2; hour++) {
            for (int minute = 0; minute < 60; minute += 5) {
                if (hour >0) {
                    String hourString = String.valueOf(hour);
                    String minuteString = String.valueOf(minute);
                    String time = hourString + " tiếng " + minuteString + " phút";
                    timeList4.add(time);}
                if (hour == 0) {
                    String minuteString = String.valueOf(minute);
                    String time = minuteString + " phút";
                    timeList4.add(time);}
            }
        }
        String[] options4 = timeList4.toArray(new String[0]);

        for (String time : options4) {
            System.out.println(time);
        }

        Button selectQ1 = findViewById(R.id.Q1);
        selectQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog(options1, (TextView) findViewById(R.id.wq1), timerData, filePath, "selectedOption1");
            }
        });
        Button selectQ2 = findViewById(R.id.Q2);
        selectQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog(options2, (TextView) findViewById(R.id.wq2), timerData, filePath, "selectedOption2");
            }
        });
        Button selectQ3 = findViewById(R.id.Q3);
        selectQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog(options3, (TextView) findViewById(R.id.wq3), timerData, filePath, "selectedOption3");
            }
        });
        Button selectQ4 = findViewById(R.id.Q4);
        selectQ4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog(options4, (TextView) findViewById(R.id.wq4), timerData, filePath, "selectedOption4");
            }
        });
        Button selectedOptionG1 = findViewById(R.id.G1);
        Button selectedOptionG2 = findViewById(R.id.G2);
        Button selectedOptionG3 = findViewById(R.id.G3);
        Button selectedOptionG4 = findViewById(R.id.G4);
        Button selectedOptionG5 = findViewById(R.id.G5);
        selectedOptionG1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog(options3, (TextView) findViewById(R.id.wg1), timerData, filePath, "selectedOptiong1");
            }
        });

        selectedOptionG2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog(options3, (TextView) findViewById(R.id.wg2), timerData, filePath, "selectedOptiong2");
            }
        });

        selectedOptionG3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog(options3, (TextView) findViewById(R.id.wg3), timerData, filePath, "selectedOptiong3");
            }
        });

        selectedOptionG4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog(options3, (TextView) findViewById(R.id.wg4), timerData, filePath, "selectedOptiong4");
            }
        });
        selectedOptionG5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog(options3, (TextView) findViewById(R.id.wg5), timerData, filePath, "selectedOptiong5");
            }
        });
    }
    private void showOptionDialog(String[] options, TextView selectedOptionTextView, TimerData timerData, String filePath, String propertyName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CLgiacngu.this);
        builder.setTitle("Chọn tùy chọn")
                .setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedOption = options[which];
                        selectedOptionTextView.setText(selectedOption);

                        if (timerData != null) {
                            switch (propertyName) {
                                case "selectedOption1":
                                    timerData.setSelectedOption1(selectedOption);
                                    break;
                                case "selectedOption2":
                                    timerData.setSelectedOption2(selectedOption);
                                    break;
                                case "selectedOption3":
                                    timerData.setSelectedOption3(selectedOption);
                                    break;
                                case "selectedOption4":
                                    timerData.setSelectedOption4(selectedOption);
                                    break;
                                case "selectedOptionG1":
                                    timerData.setSelectedOptionG1(selectedOption);
                                    break;
                                case "selectedOptionG2":
                                    timerData.setSelectedOptionG2(selectedOption);
                                    break;
                                case "selectedOptionG3":
                                    timerData.setSelectedOptionG3(selectedOption);
                                    break;
                                case "selectedOptionG4":
                                    timerData.setSelectedOptionG4(selectedOption);
                                    break;
                                case "selectedOptionG5":
                                    timerData.setSelectedOptionG5(selectedOption);
                                    break;
                            }
                            timerData.saveToJson(filePath);
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
