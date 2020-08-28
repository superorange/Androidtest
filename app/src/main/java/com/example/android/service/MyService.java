package com.example.android.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.android.R;
import com.example.android.widget.ButtonClick;

public class MyService extends Service {
    private static final String TAG = "jiatai";
    private MyHandler handler;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "service oncreate");
        handler = new MyHandler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        int type = intent.getIntExtra("type",1);
        Log.d(TAG, "the create notification type is " + type + "----" + (type == 1 ? "true" : "false"));
        if(type == 1){
            createNotificationChannel();
        }else{
            createOver();
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(startId);
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    private void createErrorNotification() {
        Notification notification = new Notification.Builder(this).build();
        startForeground(0, notification);
    }

    private void createNotificationChannel() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 通知渠道的id
        String id = "my_channel_01";
        // 用户可以看到的通知渠道的名字.
        CharSequence name = "R.string.channel_name";
//         用户可以看到的通知渠道的描述
        String description = "R.string.channel_description";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        // 为该通知设置一个id
        int notifyID = 1;
        // 通知渠道的id
        String CHANNEL_ID = "my_channel_01";
        // Create a notification and set the notification channel.
        Notification.Builder notification = new Notification.Builder(this)
                .setContentTitle("New Message") .setContentText("You've received new messages.")
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
//         配置通知渠道的属性
            mChannel.setDescription(description);
//         设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true); mChannel.setLightColor(Color.RED);
//         设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//         最后在notificationmanager中创建该通知渠道 //
            mNotificationManager.createNotificationChannel(mChannel);
            notification.setChannelId(CHANNEL_ID);
        }
        startForeground(1,notification.build());
    }
    private  void createOver(){
        String name="Channel_001";
        System.out.println("onCreate");
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder localBuilder = new Notification.Builder(this);
        localBuilder.setContentIntent(PendingIntent.getActivity(this,
                0, new Intent(this, ButtonClick.class), 0))
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.location_icon)
                .setTicker("Foreground Service Start")
                .setContentTitle("Socket服务端")
                .setContentText("正在运行...");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(name, "Notify", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            localBuilder.setChannelId(name);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            channel.setDescription("description");
//         设置通知出现时的闪灯（如果 android 设备支持的话）
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
//         设置通知出现时的震动（如果 android 设备支持的话）
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        }
        startForeground(1,localBuilder.build());
        System.out.println("开启服务执行结束");
    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            stopSelf(msg.what);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "5s onDestroy");
        Toast.makeText(this, "this service destroy", Toast.LENGTH_SHORT).show();
        stopForeground(true);
    }
}