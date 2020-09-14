package com.example.android.xuanfuchaung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.android.R;

public class XuanFuChuangActivity extends AppCompatActivity {
    Intent xuanFu;
    private  final  int REQUEST_DIALOG_PERMISSION = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuan_fu_chuang);
          xuanFu=new Intent(this,XuanFuService.class);

    }
   void requestPerssion(){
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_DIALOG_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"授权成功",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this,"授权失败",Toast.LENGTH_LONG).show();
                requestPerssion();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(xuanFu);
    }

    public void showOverFlow(View view) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(Settings.canDrawOverlays(this)){
                startService(xuanFu);
            }
            else {
                requestPerssion();
            }
        }
    }
}
