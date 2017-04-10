package com.example.since.mycampus.news;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.since.mycampus.R;
import com.example.since.mycampus.offer.OfferActivity;
import com.example.since.mycampus.offer.OfferAdapter;
import com.example.since.mycampus.offer.OfferItem;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener
{
    private List<NewsItem> newsItemList = new ArrayList<>();  //用于存放爬取的招聘信息

    private String url = "http://www.cug.edu.cn/new/List.aspx?cid=000102&pass=1&Page=";       //指定开始爬取的url

    private int page = 1;

    private TextView textViewPreviousPage;
    private TextView textViewNextPage;
    private EditText editTextPage;
    private Button buttonGo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        textViewPreviousPage = (TextView) findViewById(R.id.news_tv_previous_page);
        textViewNextPage = (TextView) findViewById(R.id.news_tv_next_page);
        editTextPage = (EditText) findViewById(R.id.news_et_page);
        buttonGo = (Button) findViewById(R.id.news_btn_go_page);
        textViewPreviousPage.setOnClickListener(this);
        textViewNextPage.setOnClickListener(this);
        buttonGo.setOnClickListener(this);

        getDataFromWeb();

    }

    private void myFunciton()
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.news_rlv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(this, newsItemList);
        recyclerView.setAdapter(adapter);
        editTextPage.setText(String.valueOf(page).toCharArray(),0,String.valueOf(page).length());

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.news_tv_previous_page:
                if (page>1)
                    --page;
                newsItemList.clear();
                getDataFromWeb();
                break;
            case R.id.news_tv_next_page:
                ++page;
                newsItemList.clear();
                getDataFromWeb();
                break;
            case R.id.news_btn_go_page:
                page = Integer.parseInt(editTextPage.getText().toString());
                newsItemList.clear();
                getDataFromWeb();
                break;
            default:
                break;
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myFunciton();

        }
    };

    private void getDataFromWeb()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Connection conn = Jsoup.connect(url+ page);
                    // 修改http包中的header,伪装成浏览器进行抓取
                    conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
                    Document doc = conn.get();
                    Elements elements = doc.getElementsByAttributeValue("width", "90%");
                    for (Element element : elements)
                    {
                        String title = element.text();
                        String href = element.select("a[href]").attr("abs:href");
                        newsItemList.add(new NewsItem(title, href));
                    }
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                handler.sendEmptyMessage(0);
            }
        }.start();

    }
}
