package com.example.since.mycampus.guide;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.since.mycampus.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GuideXuexiaojianjie extends AppCompatActivity implements Runnable
{
    private TextView textViewXuexiaojianjie;
    private String xuexiaojianjie;
    private String url = "http://www.cug.edu.cn/new/001/002.html";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_xuexiaojianjie);
        textViewXuexiaojianjie = (TextView)findViewById(R.id.tv_guidexuexiaojianjie);

        new Thread(GuideXuexiaojianjie.this).start();
    }

    @Override
    public void run()
    {
        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("content_wz");
            for (Element element : elements)
            {
                /*
                String companyName = element.getElementsByTag("h2").text(); //公司名字
                String time = element.getElementsByClass("text-default").first().text();                    //宣讲会时间
                String address = element.getElementsByClass("text-default").last().text();  //宣讲会具体地址
                String href = element.select("a[href]").attr("abs:href");       //公司招聘链接
                */
                xuexiaojianjie = element.text();
                String things = element.html();

            }

            handler.sendEmptyMessage(0);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 当收到消息时就会执行这个方法
            textViewXuexiaojianjie.setText(xuexiaojianjie);
        }
    };
}
