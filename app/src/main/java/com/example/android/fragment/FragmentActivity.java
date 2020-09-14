package com.example.android.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.android.R;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager supportFragmentManager;
    private FragmentTransaction transaction;
    private View viewById;
    private View viewById1;
    private View viewById2;
    private View viewById3;
    MyFragment f1,f2,f3,f4;
    private Bundle  bundle = new Bundle();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        supportFragmentManager = getSupportFragmentManager();
        bindView();
        viewById.performClick();

    }

    private void bindView() {
        viewById = findViewById(R.id.tv_f1);
        viewById1 = findViewById(R.id.tv_f2);
        viewById2 = findViewById(R.id.tv_f3);
        viewById3 = findViewById(R.id.tv_f4);
        viewById.setOnClickListener(this);
        viewById2.setOnClickListener(this);
        viewById3.setOnClickListener(this);
        viewById1.setOnClickListener(this);
    }
    void hideAll(FragmentTransaction transaction){
        if(f1!=null)transaction.hide(f1);
        if(f2!=null)transaction.hide(f2);
        if(f3!=null)transaction.hide(f3);
        if(f4!=null)transaction.hide(f4);
    }

    @Override
    public void onClick(View v) {
        resetSelect();

        v.setSelected(true);
        transaction =  supportFragmentManager.beginTransaction();
        hideAll(transaction);
        switch (v.getId()){
            case  R.id.tv_f1:
                if(f1==null){
                   bundle.putString("key","f1页面");
                    f1=new MyFragment();
                    f1.setArguments(bundle);
                    transaction.add(R.id.fg_test,f1,"f1");
                }
                transaction.show(f1);

                break;
            case  R.id.tv_f2:
                if(f2==null){
                    bundle.putString("key","f2页面");
                    f2=new MyFragment();
                    f2.setArguments(bundle);
                    transaction.add(R.id.fg_test,f2,"f2");
                }
                transaction.show(f2);
                break;
            case  R.id.tv_f3:
                if(f3==null){
                    bundle.putString("key","f3页面");
                    f3=new MyFragment();
                    f3.setArguments(bundle);
                    transaction.add(R.id.fg_test,f3,"f3");
                }
                transaction.show(f3);
                break;
            case  R.id.tv_f4:
                if(f4==null){
                    bundle.putString("key","f4页面");
                    f4=new MyFragment();
                    f4.setArguments(bundle);
                    transaction.add(R.id.fg_test,f4,"f4");
                }
                transaction.show(f4);
                break;
        }
        transaction.commit();
    }
        void resetSelect(){
                viewById.setSelected(false);
            viewById2.setSelected(false);
            viewById1.setSelected(false);
            viewById3.setSelected(false);
        }
}