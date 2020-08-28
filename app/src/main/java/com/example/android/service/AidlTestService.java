package com.example.android.service;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.example.android.AidlCallBackInterface;
import com.example.android.IMyAidlInterface;
import com.example.android.MainActivity;
import com.example.android.aidl_bean.AidlObject;

public class AidlTestService extends Service {
    public AidlTestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return  new MyBinder();
    }

    public class MyBinder extends IMyAidlInterface.Stub{
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }
        @Override
        public void callAidl(String text, AidlCallBackInterface callBack) throws RemoteException {
            System.out.println(text);

            callBack.callBack(new AidlObject(18,"杨帅帅","美国"),"你好啊");
//            Intent intent = new Intent(AidlTestService.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);

//           new Handler().postDelayed(new Runnable() {
//               @Override
//               public void run() {
//
//
//
//               }
//           },3000);
//            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//            builder.setMessage("是否接受文件?").setPositiveButton(
//                    "shi", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    }
//            ).setTitle("title");
//
//
//            AlertDialog ad = builder.create();
//// ad.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG); //系统中关机对话框就是这个属性
//            ad.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//
//            ad.setCanceledOnTouchOutside(false); //点击外面区域不会让dialog消失
//
//            ad.show();
//            Looper.prepare();
//            Looper.loop();
        }
    }

}
