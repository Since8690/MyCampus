package com.example.since.mycampus.guide;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.since.mycampus.R;

public class GuideXiaozhangzhici extends AppCompatActivity implements Runnable
{
    TextView textViewXiaozhangzhici;
    String url = "http://www.cug.edu.cn/new/001/001.html";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_xiaozhangzhici);
        textViewXiaozhangzhici = (TextView)findViewById(R.id.tv_xiaozhangzhici);

    }

    @Override
    public void run()
    {

    }

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 当收到消息时就会执行这个方法
        }
    };
}
