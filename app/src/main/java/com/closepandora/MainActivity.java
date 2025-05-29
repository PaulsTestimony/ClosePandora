package com.closepandora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.*;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String[] WHITELIST = {
        "https://example.com",
        "https://bank.com"
    };

    private boolean isWhitelisted(String url) {
        for (String allowed : WHITELIST) {
            if (url.startsWith(allowed)) return true;
        }
        return false;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView view = new WebView(this);
        setContentView(view);

        WebSettings settings = view.getSettings();
        settings.setJavaScriptEnabled(true);
        view.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r) {
                if (isWhitelisted(r.getUrl().toString())) {
                    return false;
                } else {
                    showBlockedDialog();
                    return true;
                }
            }
        });

        view.loadUrl(WHITELIST[0]);
    }

    private void showBlockedDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Blocked")
            .setMessage("This website is not allowed.")
            .setPositiveButton("OK", null)
            .show();
    }
}