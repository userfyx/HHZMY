package com.hhzmy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeTopActivity extends AppCompatActivity {

    private TextView title_name;
    private ImageView image;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_top);

        webview = (WebView) findViewById(R.id.webview);
        Intent intent=getIntent();
        String web=intent.getStringExtra("web");
        WebSettings wSet = webview.getSettings();
        wSet.setJavaScriptEnabled(true);
        webview.loadUrl(web);

    }



}
