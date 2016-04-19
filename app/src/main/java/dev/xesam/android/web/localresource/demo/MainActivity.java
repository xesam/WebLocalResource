package dev.xesam.android.web.localresource.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ViewFlipper;

import dev.xesam.android.web.localresource.LocalResourceHandler;
import dev.xesam.android.web.localresource.LocalResourceWebViewClient;
import dev.xesam.android.web.localresource.UrlAssetResourceRule;

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
    }

    private void testLocal() {
        vLocal.getSettings().setJavaScriptEnabled(true);
        vLocal.getSettings().setAllowFileAccess(true);
//        LocalResourceHandler localResourceHandler = new LocalResourceHandler();
//        MapAssetResourceRule rule = new MapAssetResourceRule();
//        rule.put("http://192.168.0.27/css/app_1.css", "");
//        rule.put("http://192.168.0.27/js/app_1.js", "");
//        rule.put("http://192.168.0.27/images/app_1.png", "");
//        localResourceHandler.addRule(rule);
//        vLocal.setWebViewClient(new LocalResourceWebViewClient(localResourceHandler));
        vLocal.loadUrl("file:///android_asset/local.html");
    }

    private void testRemote() {
        vRemote.getSettings().setJavaScriptEnabled(true);
        vRemote.getSettings().setAllowFileAccess(true);
        LocalResourceHandler localResourceHandler = new LocalResourceHandler();
        localResourceHandler.addRule(new UrlAssetResourceRule());
        vRemote.setWebViewClient(new LocalResourceWebViewClient(localResourceHandler));
        vRemote.loadUrl("http://192.168.0.27/3_0.html");
    }
}
