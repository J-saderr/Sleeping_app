package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Baothuc extends AppCompatActivity {
    private TextView timeEditText1;
    private TextView timeEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baothuc);

        timeEditText1 = findViewById(R.id.editTextTime1);

        timeEditText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Baothuc.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(android.widget.TimePicker view, int selectedHour, int selectedMinute) {
                                String selectedTime = selectedHour + ":" + selectedMinute;
                                timeEditText1.setText(selectedTime);
                            }
                        },
                        hour,
                        minute,
                        true
                );
                timePickerDialog.show();
            }
        });
        timeEditText2 = findViewById(R.id.editTextTime2);

        timeEditText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Baothuc.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(android.widget.TimePicker view, int selectedHour, int selectedMinute) {
                                String selectedTime = selectedHour + ":" + selectedMinute;
                                timeEditText2.setText(selectedTime);
                            }
                        },
                        hour,
                        minute,
                        true
                );
                timePickerDialog.show();
            }
        });
    }
}