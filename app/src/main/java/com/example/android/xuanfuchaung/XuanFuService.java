package com.example.android.xuanfuchaung;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.android.R;

import java.util.Timer;
import java.util.TimerTask;

public class  XuanFuService extends Service {
    int width;
   static  Handler handler;
    int distance=0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

       WindowManager manager =
               (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        width=metrics.widthPixels;
        System.out.println(metrics.xdpi+"PIXEL"+metrics.widthPixels);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            layoutParams.type=WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
        else {
            layoutParams.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.format= PixelFormat.RGBA_8888;
        layoutParams.width=400;
        layoutParams.height=200;
        layoutParams.gravity= Gravity.CENTER;
        layoutParams.x=200;
        layoutParams.y=500;
        View inflate = View.inflate(getApplicationContext(), R.layout.xuanfu_view, null);
        View viewById = inflate.findViewById(R.id.xuanfu);
        manager.addView(inflate,layoutParams);

        viewById.setOnTouchListener(new View.OnTouchListener() {
            int lastX,lastY,moveX,moveY;
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                viewById.performClick();
                //更新组件位置，不能直接用rawX，要拿到最后的位置，然后用上次的位置，加上更新的位置
                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        lastX = (int) event.getRawX();
//                        lastY = (int) event.getRawY();
//                        moveX = layoutParams.x;
//                        moveY = layoutParams.y;
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        int dx = (int) event.getRawX() - lastX;
//                        int dy = (int) event.getRawY() - lastY;
//                        layoutParams.x = moveX + dx;
//                        layoutParams.y = moveY + dy;
//                        // 更新悬浮窗位置
//                        manager.updateViewLayout(inflate, layoutParams);
//                        break;
                    case MotionEvent.ACTION_DOWN:
                        lastX= (int) event.getRawX();
                        lastY= (int) event.getRawY();
                        moveX=layoutParams.x;
                        moveY=layoutParams.y;
                        break;
                        case MotionEvent.ACTION_MOVE:
                            int tempX = (int) (event.getRawX()-lastX);
                            int tempY = (int) (event.getRawY()-lastY);
                            layoutParams.x=moveX+tempX;
                            layoutParams.y=moveY+tempY;
                            manager.updateViewLayout(inflate,layoutParams);
                            break;
                    case MotionEvent.ACTION_UP:
                        distance= (width-layoutParams.x)/3;
                        int rawX = (int) event.getRawX();
                        if(rawX!=0||rawX!=width){
                            TranslateAnimation translateAnimation=new TranslateAnimation(0,(width-rawX)/width,0,0);
                            translateAnimation.setDuration(3000);
                            translateAnimation.setFillAfter(true);
                            inflate.setAnimation(translateAnimation);
                            translateAnimation.start();
                            manager.updateViewLayout(inflate,layoutParams);
                            layoutParams.windowAnimations=android.R.style.Animation_Toast;
                            if(layoutParams.x!=width){
                                handler.sendEmptyMessage(1);
                            }
                        }
                        break;
                }

                return true;

            }

        });
        handler=new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1){
                    System.out.println("来了");
                        layoutParams.x= distance+layoutParams.x;
                        manager.updateViewLayout(inflate,layoutParams);
                    if(layoutParams.x!=width){
                        handler.sendEmptyMessageDelayed(1,100);
                    }
                }
                super.handleMessage(msg);
            }
        };




    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);

    }
}
