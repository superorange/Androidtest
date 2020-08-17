package com.example.android.widget;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class MyToast  {
    public  static Context mContext;
    public  static void show(String args){
        Toast.makeText(mContext, args,Toast.LENGTH_SHORT).show();
    }

}
