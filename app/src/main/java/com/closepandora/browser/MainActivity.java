package com.closepandora.browser;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (isAllowed(url)) {
                    return false;
                } else {
                    showBlockedDialog();
                    return true;
                }
            }
        });
        setContentView(webView);

        String startUrl = "https://example.com"; // Replace with your default allowed site
        webView.loadUrl(startUrl);
    }

    private boolean isAllowed(String url) {
        return url.startsWith("https://example.com") || url.startsWith("https://yourbank.com");
    }

    private void showBlockedDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Blocked")
                .setMessage("This site is not allowed.")
                .setPositiveButton("OK", null)
                .show();
    }
}
