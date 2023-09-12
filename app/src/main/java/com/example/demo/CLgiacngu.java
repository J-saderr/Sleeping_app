package com.example.demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class CLgiacngu extends AppCompatActivity {
    public CLgiacngu() {
        // Default constructor with no arguments
    }
    private Context context;
    private TextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clgiacngu);

        timeTextView = findViewById(R.id.danhgiagiac_ngu);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        long elapsedTimeInSeconds = sharedPreferences.getLong("elapsedTimeInSeconds", 0);

        int seconds = (int) (elapsedTimeInSeconds % 60);
        int minutes = (int) (elapsedTimeInSeconds / 60);

        timeTextView.setText(String.format("%02d:%02d", minutes, seconds));
    }
}
