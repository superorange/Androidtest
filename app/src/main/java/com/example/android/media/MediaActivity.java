package com.example.android.media;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;

import com.example.android.R;

import java.io.FileNotFoundException;

public class MediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        String[] projection=new String[]{MediaStore.Images.Media._ID,MediaStore.Images.Media.DISPLAY_NAME,MediaStore.Images.Media.DATA
                ,MediaStore.Images.Media.WIDTH, MediaStore.Images.Media.HEIGHT, MediaStore.Images.Media.DATE_ADDED,MediaStore.Images.Media.DATE_MODIFIED
                ,MediaStore.Images.Media.SIZE
        };
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_SMS
            },1);
        }
        else {
            System.out.println("start Media Query");
            ContentResolver contentResolver = getContentResolver();

            Uri parse = Uri.parse("content://sms/inbox");
            contentResolver.registerContentObserver(parse,false,new MyObserver(new Handler()));
            Cursor cursor = contentResolver.query(parse, new String[]{"address","date","type","body"}, null, null, null);
            assert cursor != null;

            while (cursor.moveToNext()){
                System.out.println(cursor.getString(0));
                System.out.println(cursor.getString(1));
                System.out.println(cursor.getString(2));
                System.out.println(cursor.getString(3));
            }
            cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            System.out.println(permissions[0]+":<>:");
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }
        }

    }

    public void insertContacts(View view) {

    }

    public void openFileSystem(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode== Activity.RESULT_OK){

        }
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("resultCode"+requestCode);
        assert data != null;
        Uri data1 = data.getData();
        assert data1 != null;
        String path = data1.getPath();
        try {
            ParcelFileDescriptor r = getContentResolver().openFileDescriptor(data1, "r");
            assert r != null;
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(r.getFileDescriptor());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String encodedPath = data1.getEncodedPath();
        System.out.println(path);
        System.out.println(encodedPath);
    }
}