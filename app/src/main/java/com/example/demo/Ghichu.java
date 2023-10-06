package com.example.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Ghichu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ghichu);
        ImageButton btnoutghichu = findViewById(R.id.btnoutghichu);
        btnoutghichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Ghichu.this);
                builder.setTitle("Chọn giao diện")
                        .setMessage("Chọn:")
                        .setPositiveButton("Đếm giờ ngủ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Ghichu.this, Timingsleep.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Home và không lưu dữ liệu đếm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Ghichu.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });
        EditText editTextTime1, editTextTime2, editTextTime3, editTextTime4, editTextTime5;
        editTextTime1 = findViewById(R.id.editTextTime1);
        editTextTime2 = findViewById(R.id.editTextTime2);
        editTextTime3 = findViewById(R.id.editTextTime3);
        editTextTime4 = findViewById(R.id.editTextTime4);
        editTextTime5 = findViewById(R.id.editTextTime5);
        String value1 = editTextTime1.getText().toString();
        String value2 = editTextTime2.getText().toString();
        String value3 = editTextTime3.getText().toString();
        String value4 = editTextTime4.getText().toString();
        String value5 = editTextTime5.getText().toString();
    }
}
