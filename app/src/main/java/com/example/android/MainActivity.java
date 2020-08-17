package com.example.android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {
    ChipGroup chipGroup;
    ListView listView;
    Spinner spinner;
    ExpandableListView exlv_1;
    ViewFlipper flipper;
   ;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner1);
//        exlv_1=findViewById(R.id.exlv_1);
        flipper=findViewById(R.id.vf_t1);
        GestureDetector gestureDetector=new GestureDetector(new MyListener());
//        flipper.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//               return  gestureDetector.onTouchEvent(motionEvent);
//            }
//        });
        flipper.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                System.out.println("1-2-3-4:"+i+"-"+i1+"-"+i2+"-"+i3);
            }
        });

       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getResources().getStringArray(R.array.languages);

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
//        chipGroup=findViewById(R.id.chip_group);
//        Canvas canvas;
        flipper.startFlipping();

    }

    public void showChip(View view) {
        Chip chipText = new Chip(this);
        chipText.setTextColor(getResources().getColor(R.color.yellow));
        chipText.setTextSize(14);
        chipText.setText("tagString");
        chipText.setChipCornerRadius(10);
        chipText.setOnClickListener(v -> {
            String keyWord = chipText.getText().toString();
            if (TextUtils.isEmpty(keyWord)) {

            } else {
                System.out.println("keyWord:"+keyWord);
            }
        });
    }
    class  MyListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {

           Toast.makeText(MainActivity.this, "ondown", LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            System.out.println("Event: "+motionEvent.getX());
            return true;
        }
    }
}


class  MyAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            view=View.inflate(view.getContext(),R.layout.activity_main,null);
            holder.imageView=view.findViewById(R.id.wrap);
            view.setTag(holder);
        }
        holder= (ViewHolder) view.getTag();
        return  view;
    }
    static class  ViewHolder{
        ImageView imageView;
    }
}