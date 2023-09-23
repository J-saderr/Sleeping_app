package com.example.demo;

import static android.app.PendingIntent.getActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class CLgiacngu extends AppCompatActivity {
    public CLgiacngu() {
        // Default constructor with no arguments
    }
    private ImageButton happyIcon;
    private ImageButton sadIcon;
    private ImageButton NofeelingIcon;
    private ImageButton badIcon;
    private String[] options3 = {"1 lần", "2 lần","3 lần","4 lần","5 lần","6 lần","7 lần","8 lần","9 lần","10 lần"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clgiacngu);
        ImageButton myImageButton = findViewById(R.id.myImageButton);
        happyIcon = findViewById(R.id.happyIcon);
        sadIcon = findViewById(R.id.sadIcon);
        NofeelingIcon = findViewById(R.id.NofeelingIcon);
        badIcon = findViewById(R.id.badIcon);

        happyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                happyIcon.setSelected(!happyIcon.isSelected());
            }
        });

        sadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sadIcon.setSelected(!sadIcon.isSelected());
            }
        });

        NofeelingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NofeelingIcon.setSelected(!NofeelingIcon.isSelected());
            }
        });

        badIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badIcon.setSelected(!badIcon.isSelected());
            }
        });
        myImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SleepFragment sleepFragment = new SleepFragment();
                fragmentTransaction.replace(R.id.fragment_sleep, sleepFragment);
                fragmentTransaction.commit();
            }
        });
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
        Button selectQ1 = findViewById(R.id.Q1);
        TextView selectedOptionTextView1 = findViewById(R.id.wq1);
        selectQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CLgiacngu.this);
                builder.setTitle("Chọn tùy chọn")
                        .setItems(options1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedOption1 = options1[which];
                                selectedOptionTextView1.setText(selectedOption1);
                                SleepFragment sleepFragment = new SleepFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("selectedOption1", selectedOption1);
                                sleepFragment.setArguments(bundle);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
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
        Button selectQ2 = findViewById(R.id.Q2);
        TextView selectedOptionTextView2 = findViewById(R.id.wq2);
        selectQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CLgiacngu.this);
                builder.setTitle("Chọn tùy chọn")
                        .setItems(options2, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedOption2 = options2[which];
                                selectedOptionTextView2.setText(selectedOption2);

                                Bundle bundle2 = new Bundle();
                                bundle2.putString("selectedOption2", selectedOption2);

                                SleepFragment fragment = new SleepFragment();
                                fragment.setArguments(bundle2);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        Button selectQ3 = findViewById(R.id.Q3);
        TextView selectedOptionTextView3 = findViewById(R.id.wq3);

        selectQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CLgiacngu.this);
                builder.setTitle("Chọn tùy chọn")
                        .setItems(options3, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedOption3 = options3[which];
                                selectedOptionTextView3.setText(selectedOption3);
                                Bundle bundle3 = new Bundle();
                                bundle3.putString("selectedOption3", selectedOption3);

                                SleepFragment fragment = new SleepFragment();
                                fragment.setArguments(bundle3);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
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
        Button selectQ4 = findViewById(R.id.Q4);
        TextView selectedOptionTextView4 = findViewById(R.id.wq4);
        selectQ4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CLgiacngu.this);
                builder.setTitle("Chọn tùy chọn")
                        .setItems(options4, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedOption4 = options4[which];
                                selectedOptionTextView4.setText(selectedOption4);
                                Bundle bundle4 = new Bundle();
                                bundle4.putString("selectedOption4", selectedOption4);

                                SleepFragment fragment = new SleepFragment();
                                fragment.setArguments(bundle4);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
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
}
