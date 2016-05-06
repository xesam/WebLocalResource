package dev.xesam.android.web.localresource.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ViewFlipper;

import dev.xesam.android.web.localresource.LocalResourceInterceptor;
import dev.xesam.android.web.localresource.LocalResourceWebViewClient;
import dev.xesam.android.web.localresource.MapAssetInterceptRule;
import dev.xesam.android.web.localresource.UrlAssetInterceptRule;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends AppCompatActivity {
    private ViewFlipper vViewFlipper;
    private WebView vLocal;
    private WebView vRemote;
    private Button vLoadLocal;
    private Button vLoadRemote3_0;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vViewFlipper = (ViewFlipper) findViewById(R.id.cll_flipper);

        vLoadLocal = (Button) findViewById(R.id.local);
        vLocal = (WebView) findViewById(R.id.cll_local);

        vLoadRemote3_0 = (Button) findViewById(R.id.remote3_0);
        vRemote = (WebView) findViewById(R.id.cll_remote);

        vLoadLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vViewFlipper.setDisplayedChild(0);
                testLocal();
            }
        });

        vLoadRemote3_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vViewFlipper.setDisplayedChild(1);
                testRemote();
            }
        });

        LocalResourceInterceptor.DEBUG = true;
    }

    private void testLocal() {
        vLocal.getSettings().setJavaScriptEnabled(true);
        vLocal.getSettings().setAllowFileAccess(true);
        LocalResourceInterceptor localResourceInterceptor = new LocalResourceInterceptor();
        MapAssetInterceptRule rule = new MapAssetInterceptRule();
        rule.put("http://xesam.github.io/html/css/app_1.css", "html/css/app.css");
        rule.put("http://xesam.github.io/html/js/app_1.js", "html/js/app.js");
        rule.put("http://xesam.github.io/html/images/app_1.png", "html/images/app.png");
        localResourceInterceptor.addRule(rule);
        vLocal.setWebViewClient(new LocalResourceWebViewClient(localResourceInterceptor));
        vLocal.loadUrl("file:///android_asset/html/local.html");
    }

    private void testRemote() {
        vRemote.getSettings().setJavaScriptEnabled(true);
        vRemote.getSettings().setAllowFileAccess(true);
        LocalResourceInterceptor localResourceInterceptor = new LocalResourceInterceptor();
        localResourceInterceptor.addRule(new UrlAssetInterceptRule());
        vRemote.setWebViewClient(new LocalResourceWebViewClient(localResourceInterceptor));
        vRemote.loadUrl("http://xesam.github.io/html/3_0.html");
    }
}
