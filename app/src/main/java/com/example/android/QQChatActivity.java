package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.android.bean.ChatBean;
import com.example.android.bean.QQChatAdapter;

public class QQChatActivity extends AppCompatActivity {
    ListView lv_chat;
    QQChatAdapter adapter;
    int a=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_q_chat);
        lv_chat=findViewById(R.id.lv_chat);
        adapter=new QQChatAdapter(this);
        lv_chat.setAdapter(adapter);
    }

    public void youSend(View view) {
            adapter.addItem(new ChatBean(
                    "你好",true
            ));
//            lv_chat.scrollTo(0,View.SCROLLBARS_INSIDE_OVERLAY);
            lv_chat.scrollTo(0,a);
            a++;
    }

    public void iSend(View view) {
        adapter.addItem(new ChatBean(
                "你好",false
        ));
    }
}