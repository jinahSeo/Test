package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.model.AndroidBridge;

public class WViewActivity extends AppCompatActivity {

    private WebView wv;
    private WebSettings wvSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wview);

        wv = findViewById(R.id.webView);


        wv.setWebViewClient(new WebViewClient());
        wv.setWebChromeClient(new WebChromeClient());

        // 웹뷰에 자바 스크립트를 사용할 수 있게 허용
        wvSettings = wv.getSettings();
        wvSettings.setJavaScriptEnabled(true);

        wv.loadUrl("https://github.com/jinahSeo");

//        wv.loadUrl("file:///");
        //자바 스크립트 브릿지 사용하도록
        AndroidBridge ab = new AndroidBridge(wv, WViewActivity.this);
        wv.addJavascriptInterface(ab, "Android");
    }

}