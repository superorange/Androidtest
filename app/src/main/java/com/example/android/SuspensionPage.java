package com.example.android;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.service.OverlayService;
import com.example.android.widget.MyToast;

public class SuspensionPage extends AppCompatActivity {
    final  int REQUEST_CODE = 1998;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspension_page);
        if(MyToast.mContext==null){
            MyToast.mContext =getApplicationContext();
        }
        System.out.println("----SuspensionPage Start");
    }

    void checkOverlayPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(Settings.canDrawOverlays(this)){
                            //有权限
                startService(new Intent(this, OverlayService.class));

            }
            else {
                //没权限
                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        if(requestCode==REQUEST_CODE){
            if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                                MyToast.show("获取权限"+permissions[0]+"成功");
            }
            else {
                MyToast.show("获取权限"+permissions[0]+"失败");
                checkOverlayPermission();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    public  static  class  MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            OverlayService.MyBinder binder = (OverlayService.MyBinder) iBinder;
            System.out.println("HashCode:"+binder.hashCode());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}