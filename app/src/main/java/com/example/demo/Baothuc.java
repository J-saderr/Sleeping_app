package com.example.demo;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Baothuc extends AppCompatActivity {
    private TextView timeEditText1;
    private TextView timeEditText2;
    private TimerData timerData; // Đối tượng TimerData để lưu trữ thời gian

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baothuc);

        ImageButton btnoutbaothuc = findViewById(R.id.btnoutbaothuc);
        btnoutbaothuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Baothuc.this);
                builder.setTitle("Chọn giao diện")
                        .setMessage("Chọn:")
                        .setPositiveButton("Đếm giờ ngủ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Baothuc.this, Timingsleep.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Home(không đếm thời gian ngủ)", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Baothuc.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });

        timeEditText1 = findViewById(R.id.editTextTime1);
        timeEditText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(timeEditText1);
            }
        });

        timeEditText2 = findViewById(R.id.editTextTime2);
        timeEditText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(timeEditText2);
            }
        });
    }

    private void showTimePickerDialog(final TextView editText) {
        final Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                Baothuc.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int selectedHour, int selectedMinute) {
                        String selectedTime = selectedHour + ":" + selectedMinute;
                        editText.setText(selectedTime);

                        if (editText.getId() == R.id.editTextTime1) {
                        }
                    }
                },
                hour,
                minute,
                true
        );
        timePickerDialog.show();
    }
}