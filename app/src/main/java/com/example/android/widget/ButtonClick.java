package com.example.android.widget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.MainActivity;
import com.example.android.R;
import com.example.android.SuspensionPage;
import com.example.android.service.MyIntentService;
import com.example.android.service.OverlayService;

public class ButtonClick extends AppCompatActivity {
    long currentSystemTime;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click);
textView=findViewById(R.id.tv_3);
    }
    public  void bindMyService(View view){
//        System.out.println("------Start Bind-----");
//        startService(new Intent(this, OverlayService.class));
//        bindService(new Intent(this, OverlayService.class),new SuspensionPage.MyConnection(),BIND_AUTO_CREATE);
//        startActivity(new Intent(this, MainActivity.class));
//        Intent intent = new Intent();
//        intent.setPackage("com.example.android");
//        intent.setAction("android.intent.action.startMyIntentService");


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                HandlerThread handler = new HandlerThread("handler");
////                handler.start();
////
////                Looper looper = handler.getLooper();
//                //                textView.setText("UPOIY");
//                Looper.prepare();
//                MyToast.show("ERROR");
//                Looper.loop();
//
//            }
//        }).start();
        run();
    }
    void run(){
        Intent intent=new Intent(this, MyIntentService.class);

        Bundle bundle = new Bundle();
        bundle.putString("key","你好");
        intent.putExtras(bundle);
//        if(ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.FOREGROUND_SERVICE)!= PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requestPermissions(new String[]{Manifest.permission.FOREGROUND_SERVICE},1);
//            }
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0&&grantResults[0]==0){
                System.out.println("授权成功");
                run();
            }
            else {
                System.out.println("授权失败");
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("按下了返回按钮");
        if(currentSystemTime==0){
            currentSystemTime= SystemClock.currentThreadTimeMillis();
            MyToast.show("请再按一次退出");
            return  false;
        }
        if(SystemClock.currentThreadTimeMillis()-currentSystemTime<=2000){
            return  true;
        }
        else {
            currentSystemTime= 0;
        }
        return false;

    }
}