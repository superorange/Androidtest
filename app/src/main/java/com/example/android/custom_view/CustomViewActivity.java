package com.example.android.custom_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.android.R;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_custom_view);
    }

    @Override
    protected void onStop() {
        WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
        layoutParams.width= WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().addContentView(getLayoutInflater().inflate(R.layout.blur,null),layoutParams);
        super.onStop();
    }
}