package com.example.android.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
//        Intent intent=new Intent(this, MyIntentService.class);
        Intent intent = new Intent();
        intent.setPackage("com.example.android");
        intent.setAction("android.intent.action.startMyIntentService");
        Bundle bundle = new Bundle();
        bundle.putString("key","你好");
        intent.putExtras(bundle);
        startService(intent);

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