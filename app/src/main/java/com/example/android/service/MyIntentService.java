package com.example.android.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;

import androidx.annotation.Nullable;

import com.example.android.MainActivity;
import com.example.android.R;
import com.example.android.widget.ButtonClick;

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private String name = "hehe";

    public MyIntentService(String name) {
        super(name);
        this.name = name;
    }

    public MyIntentService() {
        super("hehe");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle extras = intent.getExtras();
        String key = extras.getString("key");
        System.out.println("onHandleIntent:" + key);
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onCreate() {
        System.out.println("onCreate");
        super.onCreate();
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
        }
//       if(manager!=null){
//           manager.notify(1, localBuilder.build());
//       }
//       else {
//           System.out.println("Manager is null");
//       }

        startForeground(1, localBuilder.build());

    }
}
