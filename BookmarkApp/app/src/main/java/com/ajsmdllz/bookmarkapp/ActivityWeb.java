package com.ajsmdllz.bookmarkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ActivityWeb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        String URL = intent.getStringExtra("URL");

        WebView web = findViewById(R.id.webView);
        web.loadUrl(URL);

    }
}