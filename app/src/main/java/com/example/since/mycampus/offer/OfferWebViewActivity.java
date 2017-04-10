package com.example.since.mycampus.offer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.since.mycampus.R;

public class OfferWebViewActivity extends AppCompatActivity
{
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_web_view);

        webView = (WebView)findViewById(R.id.offer_webview);
        webView.getSettings().setJavaScriptEnabled(true);



        Intent intent = getIntent();
        webView.loadUrl(intent.getStringExtra("html"));
    }
}
