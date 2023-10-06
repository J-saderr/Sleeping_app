package com.example.demo;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.demo.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.action_sleep) {
                replaceFragment(new SleepFragment());
            } else if (item.getItemId() == R.id.action_chart) {
                replaceFragment(new ChartFragment());
            } else if (item.getItemId() == R.id.action_more) {
                replaceFragment(new MoreFragment());
            }
            return true;
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, new HomeFragment());
        transaction.commit();

        bottomNavigationView.setSelectedItemId(R.id.bottomNavigationView);
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }


}