package com.example.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        webView = findViewById(R.id.idWebView);
        webView.loadUrl("https://github.com/tomaximena");
        webView.setWebViewClient(new WebViewClient()); // opens in app not in browser

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack(); // goes back one page if it can
        }
        else {
            super.onBackPressed(); // destroy activity and goes back to main
        }
    }
}