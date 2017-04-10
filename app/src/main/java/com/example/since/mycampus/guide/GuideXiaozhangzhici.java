package com.example.since.mycampus.guide;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.since.mycampus.R;

public class GuideXiaozhangzhici extends AppCompatActivity
{
    private WebView webViewZhici;
    private String url = "http://www.cug.edu.cn/new/001/001.html";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_xiaozhangzhici);

        webViewZhici = (WebView)findViewById(R.id.guide_wv_zhichi);
        webViewZhici.getSettings().setSupportZoom(true);
        webViewZhici.getSettings().setBuiltInZoomControls(true);
        webViewZhici.setInitialScale(75);

        webViewZhici.loadUrl(url);
    }

}
