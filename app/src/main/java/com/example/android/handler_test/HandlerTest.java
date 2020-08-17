package com.example.android.handler_test;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Magnifier;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class HandlerTest extends AppCompatActivity {
    TextView textView;
//    int i=10;
//    EditText editText ;
//    GestureDetector gestureDetector;
//    GestureOverlayView gestureOverlayView;
//    GestureOverlayView gv_recognize;
//    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);
        textView = findViewById(R.id.tv_test3);
//        editText = findViewById(R.id.et_test);
//        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                System.out.println("beforeTextChanged");
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                System.out.println("onTextChanged");
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                System.out.println("afterTextChanged");
//            }
//        });
//        gestureDetector= new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                if(e1.getY()-e2.getY()>100){
//                    System.out.println("向下滑动");
//                }
//                else if(e1.getY()-e2.getY()<-100){
//                    System.out.println("向上滑动");
//                }
//                return super.onFling(e1, e2, velocityX, velocityY);
//            }
//        });
//        gv_recognize=findViewById(R.id.gv_recognize);
//        gestureOverlayView=findViewById(R.id.gv);
//        gestureOverlayView.addOnGesturePerformedListener(
//                (overlay, gesture) -> {
//                    Bitmap bitmap = gesture.toBitmap(128, 128, 10, 0xffff0000);
//                    View inflate = getLayoutInflater().inflate(R.layout.save, null);
//                    ImageView iv = inflate.findViewById(R.id.iv_save);
//                    iv.setImageBitmap(bitmap);
//                    new  AlertDialog.Builder(HandlerTest.this).setView(
//                            inflate
//                    ).setNeutralButton("确定", (dialog, which) -> {
//                        GestureLibrary gestureLibrary = GestureLibraries.fromFile("/mnt/sdcard/mygestures");
//                        gestureLibrary.addGesture("testMyGesture",gesture);
//                        gestureLibrary.save();
//                        Toast.makeText(HandlerTest.this,"Save Ok",Toast.LENGTH_SHORT).show();
//                    }).show();
//                }
//        );
//        gv_recognize.addOnGesturePerformedListener((overlay, gesture) -> {
//            GestureLibrary gestureLibrary = GestureLibraries.fromFile("/mnt/sdcard/mygestures");
//            if (gestureLibrary.load()) {
////                System.out.println("手势库加载成功");
//            } else {
////                System.out.println("手势库加载失败");
//            }
//
//            ArrayList<Prediction> recognize =  gestureLibrary.recognize(gesture);
//            ArrayList<String> result = new ArrayList<>();
//            for(Prediction prediction:recognize){
//                System.out.println("手势检测得分---"+prediction.score);
//                if(prediction.score>2){
//                    result.add("与手势【" + prediction.name + "】相似度为" + prediction.score);
//                }
//                if (result.size() > 0) {
//                    System.out.println("识别结束");
//                            for(String s:result){
//                                System.out.println("识别率："+s);
//                            }
//                }else{
//                    Toast.makeText(HandlerTest.this,"无法找到匹配的手势！",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if(requestCode==1){
//            if(grantResults[0]==-1){
//                Toast.makeText(this,"授权成功",Toast.LENGTH_SHORT).show();
//            }
//            else {
//                System.out.println("授权失败:"+permissions[0]);
//                Toast.makeText(this,"授权失败",Toast.LENGTH_SHORT).show();
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    public void updateText(View view) {
//        Configuration configuration = getResources().getConfiguration();
//        int g= HandlerTest.this.i++;
//        System.out.println("Main Thread Name:" + Thread.currentThread().getName());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                boolean a=Looper.getMainLooper() == Looper.myLooper();
////                Looper looper = Looper.myLooper();
////
////                System.out.println(Looper.getMainLooper().toString());
////                System.out.println("bool:"+ looper);
//                System.out.println("Update Thread Name:"+Thread.currentThread().getName());
////                HandlerTest.this.i++;
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Looper.prepare();
//                        Toast.makeText(HandlerTest.this,"Toast",Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//                    }
//                }).start();
//                    int i =10;
//                while (true){
//                                    try {
//                   Thread.sleep(1000);
//
//                                        textView.setText("Update:"+i++);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                }
//
//
//
//
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                HandlerTest.this.i++;
//                textView.setText("点击了"+i+"次");
//            }
//        }
//
//        ).start();
        new Thread(() -> textView.setText("点击了子线程")).start();

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        gestureDetector.onTouchEvent(event);
//        return false;
//    }
}
//class  MyEdit extends  androidx.appcompat.widget.AppCompatEditText{
//    private  Context mContext;
//    public MyEdit(Context context) {
//        super(context);
//    }
//
//    public MyEdit(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MyEdit(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//
//
//    void init(){
//        Drawable drawable = mContext.getResources().getDrawable(R.drawable.chip_checked);
//        Rect bounds=new Rect();
//        drawable.setBounds(bounds);
//        setCompoundDrawables(null,null,drawable,null);
//        setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
//        length();
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float rawX = event.getRawX();
//        float rawY = event.getRawY();
//        return super.onTouchEvent(event);
//    }
//}