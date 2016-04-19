package dev.xesam.android.web.localresource.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import dev.xesam.android.web.localresource.LocalResourceHandler;
import dev.xesam.android.web.localresource.LocalResourceWebViewClient;
import dev.xesam.android.web.localresource.UrlAssetResourceRule;

public class MainActivity extends AppCompatActivity {
    private WebView vWebView;
    private Button vLocal;
    private Button vRemote3_0;

    LocalResourceHandler mLocalResourceHandler;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vWebView = (WebView) findViewById(R.id.cll_web_view);
        vLocal = (Button) findViewById(R.id.local);
        vRemote3_0 = (Button) findViewById(R.id.remote3_0);

        vWebView.getSettings().setJavaScriptEnabled(true);
        vWebView.getSettings().setAllowFileAccess(true);

        mLocalResourceHandler = new LocalResourceHandler();
        mLocalResourceHandler.addRule(new UrlAssetResourceRule());
        vWebView.setWebViewClient(new LocalResourceWebViewClient(mLocalResourceHandler));

        vLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vWebView.loadUrl("file:///android_asset/local.html");
            }
        });

        vRemote3_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vWebView.loadUrl("http://192.168.0.27/3_0.html");
            }
        });
    }
}
