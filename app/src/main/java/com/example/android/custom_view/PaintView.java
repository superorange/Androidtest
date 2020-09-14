package com.example.android.custom_view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintView extends View {

    private final  Paint paint = new Paint();
    private final  Paint paintLine = new Paint();
    int w=100;
    int h=100;
    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setColor(0xffff00ff);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);
        paintLine.setColor(0xff000000);
        paintLine.setStrokeWidth(2);
        paintLine.setStyle(Paint.Style.STROKE);
        Display defaultDisplay = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics=new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        int width=outMetrics.widthPixels;
        int height=outMetrics.heightPixels;
        System.out.println("H:"+height+"W:"+width);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xffffffff);
        int width = getWidth();
        int height = getHeight();
        System.out.println("h:"+height+"W:"+width);
        for(int i=0;i<height/w;i++){
            //画横线
            canvas.drawLine((float)0,(float)i*100,(float)width,(float)i*100,paintLine);

            System.out.println("画横线:"+i);
        }
        for(int j=0;j<height/h;j++){
            //画竖线
            System.out.println("画竖线");
            canvas.drawLine((float)j*100,0,(float)j*100,(float)height,paintLine);
        }


//        canvas.drawCircle(100,100,100,paint);
//        canvas.drawArc(new RectF(100,100,200,200),0,90,true,paint);

    }
}
