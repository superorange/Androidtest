package com.example.android.permission;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class PermissionHandler {

    public  static boolean checkPermission(Context context,String permission){
        if(ActivityCompat.checkSelfPermission(context,permission)!= PackageManager.PERMISSION_GRANTED){
            return false;
        }
        return  true;
    }

}
