package com.example.android.clent;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.permission.PermissionHandler;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class ResultFragment extends Fragment {
    public  String oldValue="";
    public  String currentValue="";

    RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    if(!oldValue.equals(currentValue)){
                        oldValue=currentValue;
                        searchFile();
                    }
                }
            }
        }).start();
    }
        public void getData(CallBack callBack){
        callBack.callResult("回调函数");
        }
    private void searchFile() {
        System.out.println("Search");
        boolean b = PermissionHandler.checkPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(b){

            File root = new File("/mnt/sdcard/");
            if(!root.exists()){
                System.out.println("Null Not Found Exists");
                return;
            }
            File[] files = root.listFiles();
            find(files);
        }
        else {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }


    }
    void find(@NotNull File[] files){
        for(File file:files){
            if(file.isFile()){
                if(file.getPath().contains(currentValue)){
                    System.out.println(file.getPath());
                }
            }
            else if(file.isDirectory()){
                find(Objects.requireNonNull(file.listFiles()));
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView  = (RecyclerView) inflater.inflate(R.layout.result, container, false);
        recyclerView.setAdapter(new MyResultAdapter());

            return  inflater.inflate(R.layout.item_text,null,false);
    }
    interface  CallBack{
        void callResult(String r);
    }
}


