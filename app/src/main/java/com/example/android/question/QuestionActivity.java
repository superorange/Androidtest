package com.example.android.question;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.android.R;

public class QuestionActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nb_plus);
        textView = findViewById(R.id.test1);
    }

    public void updateText2(View view) {
        new Thread(() -> textView.setText("点击了QuestionActivity")).start();

    }
    public void updateText9(View view) {
        System.out.println("NB PLUS");
        new Thread(() -> textView.setText("点击了QuestionActivity")).start();
    }
}