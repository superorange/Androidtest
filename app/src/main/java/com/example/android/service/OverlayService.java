package com.example.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import androidx.annotation.Nullable;

public class OverlayService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("--onBind--");
        return new MyBinder();
    }
    @Override
    public void onCreate() {
        System.out.println("--onCreate--");
    }
    public  class  MyBinder extends Binder{
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        System.out.println(Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }
}
