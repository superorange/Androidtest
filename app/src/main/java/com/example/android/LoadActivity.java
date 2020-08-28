package com.example.android;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.clent.SearchActivity;
import com.example.android.service.AidlTestService;
import com.example.android.widget.ButtonClick;
import com.example.android.widget.MyToast;

import java.util.Arrays;
import java.util.Objects;

public class LoadActivity extends AppCompatActivity {

    private static final int REQUEST_DIALOG_PERMISSION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        MyToast.mContext=this;
       if( ActivityCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW)!= PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},1);
       }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        System.out.println("code"+requestCode+"per"+ Arrays.toString(permissions) +"re"+ Arrays.toString(grantResults));
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            System.out.println("授权成功");
        }
        else if(requestCode==REQUEST_DIALOG_PERMISSION){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                showSuspension();
            }
            else {

                new Handler().postDelayed(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        requestSettingCanDrawOverlays();
                    }
                }, 3000);
            }
        }
        else {
            System.out.println("授权失败");
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void goPage(View view) {
        goTo(SearchActivity.class);
//        requestSettingCanDrawOverlays();
//        bindService(new Intent(this, AidlTestService.class), new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//
//            }
//        },BIND_AUTO_CREATE);
    }
    void showSuspension(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否接受文件?").setPositiveButton(
                "shi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
        ).setTitle("title");


        AlertDialog ad = builder.create();
// ad.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG); //系统中关机对话框就是这个属性
        Objects.requireNonNull(ad.getWindow()).setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        ad.setCanceledOnTouchOutside(false); //点击外面区域不会让dialog消失

        ad.show();
    }
    void drawSuspension(){
        Window window = getWindow();
        WindowManager windowManager = window.getWindowManager();
        TextView textView = new TextView(this);
        textView.setText("你好");
        textView.setWidth(200);
        textView.setHeight(200);
        textView.setBackgroundColor(getResources().getColor(R.color.blue));
        textView.setTextColor(getResources().getColor(R.color.black));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width=200;
        attributes.height=200;
        attributes.format = PixelFormat.RGBA_8888;
        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.O){
            attributes.type=WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        }
        else {
            attributes.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        attributes.flags=WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                |WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                |WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;

        windowManager.addView(textView,attributes);


    }



    void goTo(Class<?> clazz){
        startActivity(new Intent(this,clazz));
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestSettingCanDrawOverlays() {
        if(Settings.canDrawOverlays(this)){
//           showSuspension();
            drawSuspension();
        }
      else {
            Toast.makeText(this, "请打开显示悬浮窗开关!", Toast.LENGTH_LONG).show();
            getPermission();
        }

    }
    void getPermission(){
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= Build.VERSION_CODES.O) {//8.0以上
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intent, REQUEST_DIALOG_PERMISSION);
        } else if (sdkInt >= Build.VERSION_CODES.M) {//6.0-8.0
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intent, REQUEST_DIALOG_PERMISSION);
        } else {//4.4-6.0一下
            //无需处理了
        }
    }


    public void goReceiver(View view) {
        System.out.println("goReceiver");
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        instance.registerReceiver(new MyReceiver(),new IntentFilter());
        instance.sendBroadcast(new Intent());
        instance.unregisterReceiver(new MyReceiver());
    }
}