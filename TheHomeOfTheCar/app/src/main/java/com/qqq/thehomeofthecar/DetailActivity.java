package com.qqq.thehomeofthecar;

import android.content.Intent;
import android.webkit.ClientCertRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qqq.thehomeofthecar.base.BaseActivity;

/**
 * Created by 秦谦谦 on 16/5/11 14:46.
 */
public class DetailActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.webView);


    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        String path=intent.getStringExtra("path");
        //让WebView支持js脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //这就表明当需要从一个网页跳转到另一个网页的时候,目标网页已然在当前的WebView中显示
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);//根据传入的参数再去加载新的网页
                return true;//表示当前WebView可以处理打开的新网页的请求,不用借助系统浏览器

            }
        });
        webView.loadUrl(path);
    }
}
