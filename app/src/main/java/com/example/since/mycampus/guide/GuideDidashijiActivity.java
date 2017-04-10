package com.example.since.mycampus.guide;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.since.mycampus.R;
import com.example.since.mycampus.news.NewsAdapter;
import com.example.since.mycampus.offer.OfferActivity;
import com.example.since.mycampus.offer.OfferAdapter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuideDidashijiActivity extends AppCompatActivity implements Runnable
{
    List<GuideShijiItem> shijiItemList = new ArrayList<>();
    private String url = "http://www.cug.edu.cn/new/001/007.html";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_didashiji);

        new Thread(GuideDidashijiActivity.this).start();

    }

    private void myFunciton()
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_didashiji);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GuideShijiAdapter adapter = new GuideShijiAdapter(this, shijiItemList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void run()
    {
        try
        {
            Connection conn = Jsoup.connect(url);
            // 修改http包中的header,伪装成浏览器进行抓取
            conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
            Document doc = conn.get();


            Elements elements = doc.getElementsByClass("link2");
            int elementsSize = elements.size();

            for (int i=1; i<elementsSize; i++)
            {
                Element element = elements.get(i);

                String title = element.text();
                String href = element.select("a[href]").attr("abs:href");

                GuideShijiItem shijiItem = new GuideShijiItem(title, href);
                shijiItemList.add(shijiItem);

            }
            handler.sendEmptyMessage(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            // 当收到消息时就会执行这个方法
            myFunciton();
        }
    };
}