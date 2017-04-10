package com.example.since.mycampus.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.since.mycampus.R;

public class NewsWebViewActivity extends AppCompatActivity
{
    private TextView textViewNewsTitle;
    private WebView webViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web_view);

        textViewNewsTitle = (TextView)findViewById(R.id.news_tv_title);
        webViewNews = (WebView)findViewById(R.id.news_webview);

        webViewNews.getSettings().setSupportZoom(true);
        webViewNews.getSettings().setBuiltInZoomControls(true);
        webViewNews.setInitialScale(75);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        NewsItem newsItem = (NewsItem) bundle.getSerializable("newsClass");
        textViewNewsTitle.setText(newsItem.getTitle());
        webViewNews.loadUrl(newsItem.getHref());

    }
}

