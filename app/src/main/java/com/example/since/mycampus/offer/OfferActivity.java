package com.example.since.mycampus.offer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.since.mycampus.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OfferActivity extends AppCompatActivity implements Runnable
{
    private List<OfferItem> offerItemList = new ArrayList<>();  //用于存放爬取的招聘信息

    private String url = "http://cug.91wllm.com/teachin/index?page=1";       //指定开始爬取的url

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        new Thread(OfferActivity.this).start();
    }

    private void myFunciton()
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_offer);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        OfferAdapter adapter = new OfferAdapter(OfferActivity.this ,offerItemList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void run()
    {
        try
        {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("list-group-item");
            for (Element element : elements)
            {
                String companyName = element.getElementsByTag("h2").text(); //公司名字
                String time = element.getElementsByClass("text-default").first().text();                    //宣讲会时间
                String address = element.getElementsByClass("text-default").last().text();  //宣讲会具体地址
                String href = element.select("a[href]").attr("abs:href");       //公司招聘链接

                OfferItem offerItem = new OfferItem(companyName, address, time, href);
                offerItemList.add(offerItem);
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
            myFunciton();
        }
    };
}
