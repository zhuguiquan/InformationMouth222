package com.bw.informationmouth;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;

import com.bw.informationmouth.base.BaseActivity;

import java.net.URL;

public class SecondActivity extends BaseActivity {

    private WebView webView;
    private String key;

    @Override
    protected void initData() {
        key = getIntent().getStringExtra("key");
        webView.loadUrl(key);
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.web);
        //WebSettings
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //实现应用内加载
                view.loadUrl(key);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("TAG", "页面开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("TAG", "页面加载完成");
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e("TAG", "进度改变"+newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e("TAG", "网页标题："+title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                Log.e("TAG", "接受到网页图标");
            }
        });

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }
}
