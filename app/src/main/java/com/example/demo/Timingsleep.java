package com.example.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class Timingsleep extends AppCompatActivity {
    private ImageView swipeView;
    private GestureDetector gestureDetector;
    private Handler handler;
    private TextView timerTextView;
    private long elapsedTimeInSeconds = 0;
    private Timer timer;
    private TimerViewModel timerViewModel;
    private String timerDataJson;

    protected void onResume() {
        super.onResume();
        timerViewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        timerViewModel.startTimer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timingsleep);
        timerDataJson = getIntent().getStringExtra("timerDataJson");
        timerTextView = findViewById(R.id.timerTextView);
        swipeView = findViewById(R.id.swipeView);
        timer = new Timer();
        handler = new Handler();
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getY() > e2.getY()) {
                    Toast.makeText(Timingsleep.this, "Trượt lên để dừng", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        // Set up the onTouchListener for swipeView
        swipeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    stopTimer();
                    long currentTimeInMillis = System.currentTimeMillis();
                    long startTimeInMillis = getIntent().getLongExtra("startTimeInMillis", 0);
                    elapsedTimeInSeconds += (currentTimeInMillis - startTimeInMillis) / 1000;
                }
                return true;
            }
        });
        Button btnbaothuc;
        btnbaothuc = findViewById(R.id.hendongho);
        btnbaothuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Timingsleep.this, Baothuc.class);
                startActivity(intent);
            }
        });
    }

    private void stopTimer() {
        if (timerViewModel != null) {
            timerViewModel.stopTimer();
            long currentTimeInMillis = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date resultDate = new Date(currentTimeInMillis);
            String formattedTime = sdf.format(resultDate);

            long startTimeInMillis = getIntent().getLongExtra("startTimeInMillis", 0);
            elapsedTimeInSeconds += (currentTimeInMillis - startTimeInMillis) / 1000;

            long hours = elapsedTimeInSeconds / 3600;
            long remainingSeconds = elapsedTimeInSeconds % 3600;
            long minutes = remainingSeconds / 60;
            long seconds = remainingSeconds % 60;

            formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            timerTextView.setText(formattedTime);

            if (elapsedTimeInSeconds >= 30 * 60) {
                TimerData timerData = new TimerData(startTimeInMillis, currentTimeInMillis);

                String filePath = getFilesDir() + "/data.json";
                timerData.saveToJson(filePath);

                Intent intent = new Intent(Timingsleep.this, CLgiacngu.class);
                startActivity(intent);
                finish();
            }
            if (elapsedTimeInSeconds < 30 * 60) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Thời gian nhỏ hơn 30 phút. Bạn có muốn dừng không?")
                        .setPositiveButton("Dừng", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean shouldSaveTime = false;
                                Intent intent = new Intent(Timingsleep.this, CLgiacngu.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Tiếp tục", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.create().show();
            }
        }

        // Remove any pending Runnable tasks from the handler
        handler.removeCallbacksAndMessages(null);
        }
    }