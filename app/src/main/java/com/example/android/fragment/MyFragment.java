package com.example.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.R;

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("---onCreateView---");
        View inflate = inflater.inflate(R.layout.my_fragment, container, false);
        TextView viewById = inflate.findViewById(R.id.fg_tv);
        Bundle arguments = getArguments();
        if(arguments!=null){
            System.out.println("key:"+arguments.getString("key"));
            viewById.setText(arguments.getString("key"));
        }
        return  inflate;
    }
}
