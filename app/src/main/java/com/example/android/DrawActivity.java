package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawViewTest(this));
    }
}