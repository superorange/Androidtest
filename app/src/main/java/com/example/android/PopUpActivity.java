package com.example.android;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.PopupWindowCompat;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class PopUpActivity extends AppCompatActivity  {
    String content="上面代码就实现了颜色效果下面一步一步学习下首先()参数一表示样式上面的就是一种样式常用的样式如下为文本设置前景色为文本设置文本背景色为文本设置相对大小在原有的文字大小的基础上相对设置文字大小为文本设置中划线也就是常说的删除线为文本设置下划线为文本设置上标可以配合做数学公式中的上标为文本设置下标为文本设置粗体斜体风格作者链接来源简书著作权归作者所有商业转载请联系作者获得授权非商业转载请注明出处";
//    LinearLayout linearLayout;
    TextView textView;
    double rx;
    double ry;
    boolean show =false;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        for (int i = 0; i < 5; i++) {
            content+=content;
        }
        setContentView(R.layout.activity_pop_up);

//        linearLayout=findViewById(R.id.ll_test);
        textView=findViewById(R.id.tv_test);
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(content);

        for(int i=0;i<content.length();i++){
            spannableString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View v) {
                    TextView tv = (TextView) v;
//                    String s = tv
//                            .getText()
//                            .subSequence(tv.getSelectionStart(),
//                                    tv.getSelectionEnd()).toString();
                    System.out.println("tv.getSelectionStart():"+tv.getSelectionStart());
                    showPopUpWindow(v,rx,ry,"s");
                }
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);

                    ds.setColor(0xfffeac12);       //设置文件颜色
                    ds.setUnderlineText(false);      //设置下划线
                }
            },i,i+1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        textView.setText(spannableString);





        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                long millis1 = SystemClock.currentThreadTimeMillis();
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    long millis2 = SystemClock.currentThreadTimeMillis();
                    if(millis2-millis1<=500){
                        TextView widget = (TextView) view;
                        Object text = widget.getText();
                        if (text instanceof Spanned) {
                            Spannable buffer = (Spannable) text;

                            rx=motionEvent.getRawX();
                            ry=motionEvent.getRawY();
                            int x = (int) motionEvent.getX();
                            int y = (int) motionEvent.getY();

                            x -= widget.getTotalPaddingLeft();
                            y -= widget.getTotalPaddingTop();

                            x += widget.getScrollX();
                            y += widget.getScrollY();

                            Layout layout = widget.getLayout();
                            int line = layout.getLineForVertical(y);
                            int off = layout.getOffsetForHorizontal(line, x);

                            ClickableSpan[] link = buffer.getSpans(off, off,
                                    ClickableSpan.class);

                            if (link.length != 0) {
                                        link[0].onClick(widget);
                                        Selection.setSelection(buffer,
                                        buffer.getSpanStart(link[0]),
                                        buffer.getSpanEnd(link[0]));
                                return true;
                            }

                        }


                       show=true;
                    }
                    show=false;
                }
                return true;
            }
        });
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //若没有绑定clickableSpan，无法使用subSequence方法
//                //若tv.getSelectionStart()-1,则输出点击的文字以及其上一个文字
//                //若tv.getSelectionEnd()+1,则输出点击的文字以及其下一个文字，如此类推
//                //通过标点判断还可截取一段文字中我们所点击的那句话
//                TextView tv = (TextView) v;
//                System.out.println("tv.getSelectionStart():"+tv.getSelectionStart());
//                System.out.println("tv.getSelectionEnd():"+tv.getSelectionEnd());
//                String s = tv
//                        .getText()
//                       .toString();
//                Log.d("tapped on:", s);
//            }
//        });



    }
    class  MySpan extends  ClickableSpan{

        @Override
        public void onClick(@NonNull View view) {

        }
    }

    public void showPopUpWindow(View view) {
        View inflate = getLayoutInflater().inflate(R.layout.item_pop_window, null, false);

        PopupWindow window =new PopupWindow(
                inflate, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,true
        );
//        window.setAnimationStyle(R.animator.bg);
        window.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
//        getWindow().getWindowManager().getCurrentWindowMetrics().getBounds()
//        System.out.println("Y:"+y);



        window.showAtLocation(view, Gravity.TOP,300,300);
//        window.showAsDropDown(view);

    }

    public void showPopUpWindow(View view,double x,double y,String text) {
        View inflate = getLayoutInflater().inflate(R.layout.item_pop_window, null, false);
        TextView textView = inflate.findViewById(R.id.tv_test2);
        textView.setText(text);
        PopupWindow window =new PopupWindow(
                inflate, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,false
        );
//        window.setAnimationStyle(R.animator.bg);
        window.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
//        getWindow().getWindowManager().getCurrentWindowMetrics().getBounds()
//        System.out.println("Y:"+y);




        window.showAtLocation(view, Gravity.NO_GRAVITY, ((Double)x).intValue(), ((Double)y).intValue());
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               window.dismiss();
           }
       },1000);
//        window.showAsDropDown(view);

    }

}