package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private TimerViewModel timerViewModel;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button startButton;
        Button BaothucButton;
        Button TodolistButton;
        startButton = view.findViewById(R.id.batdaungu);
        BaothucButton = view.findViewById(R.id.baothuc);
        TodolistButton = view.findViewById(R.id.todolist);
        timerViewModel = new ViewModelProvider(requireActivity()).get(TimerViewModel.class);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long startTimeInMillis = System.currentTimeMillis();
                Date currentTime = new Date(startTimeInMillis);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String formattedDateTime = dateFormat.format(currentTime);

                TimerData timerData = new TimerData();
                timerData.setStart(startTimeInMillis);

                String filename = "data.json";
                String filePath = getActivity().getFilesDir().getPath() + "/" + filename;

                File jsonFile = new File(filePath);
                if (jsonFile.exists()) {
                    // Tệp JSON tồn tại, bạn có thể tiến hành đọc nội dung từ tệp JSON ở đây
                } else {
                    // Tệp JSON không tồn tại, có thể có lỗi khi lưu dữ liệu
                }

                Intent intent = new Intent(getActivity(), Timingsleep.class);
                intent.putExtra("timerDataJsonFilePath", filePath);
                intent.putExtra("startTimeInMillis", startTimeInMillis);
                startActivity(intent);

                timerViewModel.setStartTimeInMillis(startTimeInMillis);
                timerViewModel.startTimer();
            }
        });
        BaothucButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Baothuc.class);
                startActivity(intent);
            }
            });
        TodolistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Todolist.class);
                startActivity(intent);
            }
        });
        return view;
    }
}

