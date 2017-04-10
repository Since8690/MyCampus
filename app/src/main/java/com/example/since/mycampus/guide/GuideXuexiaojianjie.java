package com.example.since.mycampus.guide;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.since.mycampus.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GuideXuexiaojianjie extends AppCompatActivity
{
    private WebView webViewJianjie;
    private String url = "http://www.cug.edu.cn/new/001/002.html";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_xuexiaojianjie);
        webViewJianjie = (WebView)findViewById(R.id.guide_wv_jianjie);

        webViewJianjie.getSettings().setSupportZoom(true);
        webViewJianjie.getSettings().setBuiltInZoomControls(true);
        webViewJianjie.setInitialScale(75);

        webViewJianjie.loadUrl(url);
    }
}
