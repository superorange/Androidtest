package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android.widget.ButtonClick;
import com.example.android.widget.MyToast;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        MyToast.mContext=this;
    }

    public void goPage(View view) {
        goTo(ButtonClick.class);
    }
    void goTo(Class<?> clazz){
        startActivity(new Intent(this,clazz));
    }
}