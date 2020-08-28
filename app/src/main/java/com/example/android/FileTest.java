package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileFilter;

public class FileTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_test);

    }

    public void fileText(View view) {
        File rootDirectory = Environment.getRootDirectory();
        File[] files = rootDirectory.listFiles();

        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File[] files1 = externalStorageDirectory.listFiles();
        for(File file:files1){
            System.out.println(file.getPath());
        }
        System.out.println("externalStorageDirectory:"+externalStorageDirectory.getAbsolutePath());
        System.out.println(rootDirectory.getAbsolutePath());

    }
}