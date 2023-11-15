package com.example.gorttorganbrowser;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.WebView);
        WebSettings ws = web.getSettings();
        ws.setJavaScriptEnabled(true);
        web.loadUrl("https://www.google.com");
        web.setWebViewClient(new WebViewClient());
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (web.canGoBack()) {
                    web.goBack();
                }
            }
        };
        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
        onBackPressedDispatcher.addCallback(this, callback);
    }

    public void onSearch(View view) {
        EditText edt = findViewById(R.id.EditText);
        String req = edt.getText().toString();
        if(!req.contains("https://")){
            req = "https://" + req;
        }
        edt.setText(req);
        web.loadUrl(req);
    }
}