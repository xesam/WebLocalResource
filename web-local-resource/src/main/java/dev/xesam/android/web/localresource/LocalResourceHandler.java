package dev.xesam.android.web.localresource;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xesamguo@gmail.com on 10/16/15.
 */
public class LocalResourceHandler {

    public static final boolean DEBUG = false;

    public List<ResourceRule> rules = new ArrayList<>();

    public void addRule(ResourceRule rule) {
        rules.add(rule);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected WebResourceResponse shouldInterceptRequest(Context context, Uri uri, WebResourceResponse defaultResource) {
        if (DEBUG) {
            Log.d("shouldInterceptRequest", uri.toString());
        }
        if (rules == null || rules.size() == 0) {
            return defaultResource;
        }
        for (ResourceRule rule : rules) {
            WebResourceResponse webResourceResponse = rule.shouldInterceptRequest(context, uri);
            if (webResourceResponse != null) {
                return webResourceResponse;
            }
        }
        return defaultResource;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WebResourceResponse shouldInterceptRequest(Context context, WebResourceRequest request, WebResourceResponse defaultResource) {
        return shouldInterceptRequest(context, request.getUrl(), defaultResource);
    }

    public WebResourceResponse shouldInterceptRequest(Context context, String url, WebResourceResponse defaultResource) {
        return shouldInterceptRequest(context, Uri.parse(url), defaultResource);
    }
}
