package com.example.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Timingsleep extends AppCompatActivity {
    private ImageView swipeView;
    private GestureDetector gestureDetector;
    private Handler handler;
    private TextView timerTextView;
    private long elapsedTimeInSeconds = 0;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timingsleep);

        timerTextView = findViewById(R.id.timerTextView);
        swipeView = findViewById(R.id.swipeView);

        handler = new Handler();
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getY() > e2.getY()) {
                    Toast.makeText(Timingsleep.this, "Vuốt lên để tạm dừng", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        swipeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Toast.makeText(Timingsleep.this, "Vuốt lên để tạm dừng", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Timingsleep.this, CLgiacngu.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    private void handleEnd() {
        isTimerRunning = false;
        handler.removeCallbacksAndMessages(null);
        saveTime();
        stopTimer();
        Intent intent = new Intent(Timingsleep.this, CLgiacngu.class);
        startActivity(intent);
        finish();
    }

    private void saveTime() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("elapsedTimeInSeconds", elapsedTimeInSeconds);
        editor.apply();
    }

    private void updateTimerText() {
        int seconds = (int) (elapsedTimeInSeconds % 60);
        int minutes = (int) (elapsedTimeInSeconds / 60);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
            }
        });
    }

    private void stopTimer() {
        isTimerRunning = false;
        handler.removeCallbacks(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isTimerRunning) {
                elapsedTimeInSeconds++;
                updateTimerText();
                handler.postDelayed(this, 1000);
            }
        }
    };
}
