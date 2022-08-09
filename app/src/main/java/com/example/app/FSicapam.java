package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class FSicapam extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsicapam);

        webView = findViewById(R.id.webview);

        Bundle reciveData = getIntent().getExtras();
        String Uri = reciveData.getString("QRContent");


        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
        //httpIntent.setData(Uri.parse(QRContent));
        startActivity(httpIntent);


    }




}