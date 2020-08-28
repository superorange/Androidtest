package com.example.android.clent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.android.R;

public class SearchActivity extends AppCompatActivity {
    EditText editText;
    View fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar actionBar = getActionBar();
        androidx.appcompat.app.ActionBar supportActionBar = getSupportActionBar();

        if(actionBar!=null){
            actionBar.hide();
        }
        if(supportActionBar!=null){
            supportActionBar.hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText= findViewById(R.id.et_search);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fg_search,new SearchFragment(),"search");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("change:"+s.toString());
                ResultFragment result = (ResultFragment) fragmentManager.findFragmentByTag("result");
                if(result==null){
                    fragmentTransaction.replace(R.id.fg_search,new ResultFragment(),"result").addToBackStack("d").commit();
                }
                else {

                    Bundle bundle = new Bundle();
                    bundle.putString("s",s.toString());
                    result.getData(new ResultFragment.CallBack() {
                        @Override
                        public void callResult(String r) {
                            System.out.println(r);
                        }
                    });

                }


            }
        });
    }

    public void back(View view) {
        finish();
    }
}