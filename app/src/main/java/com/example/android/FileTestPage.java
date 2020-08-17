package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTestPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_test_page);
    }

    public void fileSave(View view)  {
        File file = new File("/mnt/sdcard/test/test3.png");
        Bitmap bitmapFactory = BitmapFactory.decodeResource(
                getResources(),R.mipmap.t2
        );

       if( !file.exists()){
           try {
               file.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       String content = "Hello Android";
        try {
            FileOutputStream fileOutputStream =new FileOutputStream(file);
//            fileOutputStream.write(content.getBytes());
            bitmapFactory.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}