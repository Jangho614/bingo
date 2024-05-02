package com.example.myapp_230604;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class notice extends AppCompatActivity {
    ImageView back_btn;
    Button notice_rm;
    Button notice_adj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        back_btn = findViewById(R.id.back_btn5);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), noticeFragment_m.class);
                startActivity(intent);
            }
        });
        notice_rm = findViewById(R.id.notice_rm);
        notice_rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"게시글 삭제",Toast.LENGTH_SHORT).show();
            }
        });
        notice_adj = findViewById(R.id.notice_adj);
        notice_adj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"게시글 수정",Toast.LENGTH_SHORT).show();
            }
        });

    }
}