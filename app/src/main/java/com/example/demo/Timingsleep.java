package com.example.demo;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class Timingsleep extends AppCompatActivity {
    private ImageView swipeView;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timingsleep);
        swipeView = findViewById(R.id.swipeView);
        try {
            // Mã của bạn trong Activity Timingsleep
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Tạo một GestureDetector
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // Xử lý sự kiện vuốt lên
                if (e1.getY() > e2.getY()) {
                    // Người dùng đã vuốt lên
                    Toast.makeText(Timingsleep.this, "Vuốt lên để tạm dừng", Toast.LENGTH_SHORT).show();
                    // Thêm xử lý tạm dừng ở đây
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        swipeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Xử lý sự kiện vuốt lên
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Người dùng đã vuốt lên
                    Toast.makeText(Timingsleep.this, "Vuốt lên để tạm dừng", Toast.LENGTH_SHORT).show();

                    // Bắt đầu một Activity mới
                    Intent intent = new Intent(Timingsleep.this, CLgiacngu.class);
                    startActivity(intent);

                    return true;
                }
                return true;
            }
        });
    }
}