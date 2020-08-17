package com.example.android.bean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.R;

import java.util.ArrayList;
import java.util.List;

public class QQChatAdapter extends BaseAdapter {
    final Context mContext;
    ViewHolder holder=null;
    List<ChatBean> msgList = new ArrayList<>();
    public QQChatAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void addItem(ChatBean bean){
        msgList.add(bean);

        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            holder=new ViewHolder();
            if(msgList.get(i).isMe){
               view=View.inflate(mContext,R.layout.chat_box_right,null);
                holder.imageView=view.findViewById(R.id.iv_chat_r);
                holder.textView=view.findViewById(R.id.tv_chat_r);
                Drawable drawable=new Drawable() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void draw(@NonNull Canvas canvas) {
                        Rect rect = new Rect(0,0,100,100);
                        canvas.drawColor(R.color.yellow);
                    }

                    @Override
                    public void setAlpha(int i) {

                    }

                    @Override
                    public void setColorFilter(@Nullable ColorFilter colorFilter) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public int getOpacity() {
                        return 1;
                    }
                };
                holder.textView.setBackground(drawable);
            }
            else {
                view=View.inflate(mContext,R.layout.chat_box_left,null);
                holder.imageView=view.findViewById(R.id.iv_chat_l);
                holder.textView=view.findViewById(R.id.tv_chat_l);
            }
            view.setTag(holder);
        }
        holder= (ViewHolder) view.getTag();
        holder.setText(msgList.get(i).text);
        return view;
    }
    static  class  ViewHolder{
            ImageView imageView;
            TextView textView;
            void setText(String text){
                textView.setText(text);
            }
    }
}

