package dev.xesam.android.web.localresource;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * a helper client
 * Created by xesamguo@gmail.com on 16-4-19.
 */
public class LocalResourceWebViewClient extends WebViewClient {

    private LocalResourceHandler mLocalResourceHandler;

    public LocalResourceWebViewClient(LocalResourceHandler localResourceHandler) {
        mLocalResourceHandler = localResourceHandler;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        WebResourceResponse defaultResource = super.shouldInterceptRequest(view, url);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return defaultResource;
        } else {
            return mLocalResourceHandler.shouldInterceptRequest(view.getContext(), url, defaultResource);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        WebResourceResponse defaultResource = super.shouldInterceptRequest(view, request);
        return mLocalResourceHandler.shouldInterceptRequest(view.getContext(), request, defaultResource);
    }
}
