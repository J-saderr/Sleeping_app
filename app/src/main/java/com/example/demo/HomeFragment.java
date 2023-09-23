package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.lifecycle.ViewModelProvider;

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
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Timingsleep.class);
                long startTimeInMillis = System.currentTimeMillis();
                intent.putExtra("startTimeInMillis", startTimeInMillis);
                startActivity(intent);// Capture the start time// Start the timer using the ViewModel
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

