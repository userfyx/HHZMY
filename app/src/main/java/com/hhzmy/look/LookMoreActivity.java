package com.hhzmy.look;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.hhzmy.main.R;

public class LookMoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_more);

        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        WebView web= (WebView) findViewById(R.id.webView_url);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);
    }
}
