package com.example.android.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

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
//        Bundle extras = intent.getExtras();
//        String key = extras.getString("key");
//        System.out.println("onHandleIntent:" + key);
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onCreate() {

//       if(manager!=null){
//           manager.notify(1, localBuilder.build());
//       }
//       else {
//           System.out.println("Manager is null");
//       }


        super.onCreate();
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

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
         intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "")
                .setContentTitle("服务运行于前台")
                .setContentText("service被设为前台进程")
                .setSmallIcon(R.mipmap.love)
                .setTicker("service正在后台运行...")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
//        startForeground(0, notification);
        return super.onStartCommand(intent, flags, startId);
    }
}
