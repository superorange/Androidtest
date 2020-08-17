package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class NbPlus extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        textView = findViewById(R.id.tv_1);
    }

    public void updateText(View view) {
        System.out.println("NB PLUS");
        new Thread(() -> {
            textView.setText("Click Me!!!");
            System.out.println(Thread.currentThread().getName());
        }).start();
    }
}