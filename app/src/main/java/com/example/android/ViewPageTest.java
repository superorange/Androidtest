package com.example.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewPageTest extends AppCompatActivity {
    TextView textView;
    ViewPager viewPager;
    View translationView;
    int oldPosition=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_test);
        View inflate1 = getLayoutInflater().inflate(R.layout.view_page_item, null);
        View inflate2 = getLayoutInflater().inflate(R.layout.view_page_item, null);
        View inflate3 = getLayoutInflater().inflate(R.layout.view_page_item, null);
        translationView=findViewById(R.id.view_translation);
        viewPager = findViewById(R.id.vp_1);

        inflate1.setBackgroundColor(0xffFFB6C1);
        inflate2.setBackgroundColor(0xff7B68EE);
        inflate3.setBackgroundColor(0xffADD8E6);
        TextView textView1= inflate1.findViewById(R.id.tv_2);
        TextView textView2= inflate2.findViewById(R.id.tv_2);
        TextView textView3= inflate3.findViewById(R.id.tv_2);
        textView1.setText("第    1   个页面");
        textView2.setText("第    2   个页面");
        textView3.setText("第    3   个页面");
        ArrayList<View> data = new ArrayList<>();
        data.add(inflate1);
        data.add(inflate2);
        data.add(inflate3);

        MyPageAdapter  adapter=new MyPageAdapter(data);
        viewPager.setAdapter(adapter);
        viewPager.getCurrentItem();
        adapter.notifyDataSetChanged();
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                position++;
                System.out.println("position:"+position);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                Display defaultDisplay = getWindowManager().getDefaultDisplay();
                defaultDisplay.getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;
                int offset = width/3;
                System.out.println("width:"+width);

                TranslateAnimation translateAnimation=new TranslateAnimation(
                       offset*oldPosition ,offset*position ,0,0);
                Animation loadAnimation = AnimationUtils.loadAnimation(
                        ViewPageTest.this, R.anim.tab_translation
                );
                translateAnimation.setFillAfter(true);
                translateAnimation.setDuration(300); //设置动画时间为300毫秒
                translationView.startAnimation(translateAnimation);
                oldPosition = position;
            }

        });
    }
}

class  MyPageAdapter extends PagerAdapter{
    List<View> adpter;
    MyPageAdapter(List<View> adpter){
        this.adpter = adpter;
    }
    @Override
    public int getCount() {
        return adpter.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(adpter.get(position));
        return adpter.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(adpter.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence text = ((TextView) ((ViewGroup) adpter.get(position)).getChildAt(0)).getText();
        return text;
    }


}
