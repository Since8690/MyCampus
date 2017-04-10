package com.example.since.mycampus.guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.since.mycampus.R;
import com.example.since.mycampus.news.NewsItem;

public class GuideShijiWebView extends AppCompatActivity
{
    private WebView webViewShiji;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_shiji_web_view);

        webViewShiji = (WebView)findViewById(R.id.guide_ww_shiji);

        webViewShiji.getSettings().setSupportZoom(true);
        webViewShiji.getSettings().setBuiltInZoomControls(true);
        webViewShiji.setInitialScale(75);

        Intent intent = getIntent();
        String url = intent.getStringExtra("html");
        webViewShiji.loadUrl(url);
    }
}
