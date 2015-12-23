package dev.xesam.android.web.localresource.demo;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import dev.xesam.android.web.localresource.LocalResourceHandler;

public class MainActivity extends AppCompatActivity {
    private WebView vWebView;
    private Button vLocal;
    private Button vRemote2_3;
    private Button vRemote3_0;

    LocalResourceHandler mLocalResourceHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vWebView = (WebView) findViewById(R.id.cll_web_view);
        vLocal = (Button) findViewById(R.id.local);
        vRemote2_3 = (Button) findViewById(R.id.remote2_3);
        vRemote3_0 = (Button) findViewById(R.id.remote3_0);

        vWebView.getSettings().setJavaScriptEnabled(true);
        vWebView.getSettings().setAllowFileAccess(true);
        mLocalResourceHandler = new LocalResourceHandler();

        vWebView.setWebViewClient(new WebViewClient() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                Log.e("shouldInterceptRequest", url);
                WebResourceResponse webResourceResponse = mLocalResourceHandler.shouldInterceptRequest(MainActivity.this, url);
                if (webResourceResponse == null) {
                    return super.shouldInterceptRequest(view, url);
                } else {
                    return webResourceResponse;
                }
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Log.e("shouldInterceptRequest", request.getMethod() + ":" + request.getUrl());
                WebResourceResponse webResourceResponse = mLocalResourceHandler.shouldInterceptRequest(MainActivity.this, request);
                if (webResourceResponse == null) {
                    return super.shouldInterceptRequest(view, request);
                } else {
                    return webResourceResponse;
                }
            }
        });

        vLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vWebView.loadUrl("file:///android_asset/local.html");
            }
        });

        vRemote2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vWebView.loadUrl("http://192.168.1.102/remote2_3.html");
            }
        });

        vRemote3_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vWebView.loadUrl("http://192.168.1.102/remote3_0.html");
            }
        });
    }
}
