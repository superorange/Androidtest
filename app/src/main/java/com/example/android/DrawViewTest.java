package com.example.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawViewTest extends View {
    Paint paint = new Paint();
    float startX;
    float startY;
    float endX;
    float endY;
    public DrawViewTest(Context context) {
        super(context);
        paint.setColor(0xff4B0082);
       paint.setStrokeWidth(2);
    }

    public DrawViewTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawViewTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        canvas.drawLine(startX,startY,endX,endY,paint);
        canvas.drawPoint(endX,endY,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("listen----->");
        startX=endX;
        startY=endY;
        endX=event.getRawX();
        endY=event.getRawY();
        return true;
    }
}
