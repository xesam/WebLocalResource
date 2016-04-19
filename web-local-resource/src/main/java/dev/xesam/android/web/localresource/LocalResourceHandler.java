package dev.xesam.android.web.localresource;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xesamguo@gmail.com on 10/16/15.
 */
public class LocalResourceHandler {

    public List<ResourceRule> rules = new ArrayList<>();

    public void addRule(ResourceRule rule) {
        rules.add(rule);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public WebResourceResponse shouldInterceptRequest(Context context, Uri uri) {
        if (rules == null || rules.size() == 0) {
            return null;
        }
        for (ResourceRule rule : rules) {
            WebResourceResponse webResourceResponse = rule.shouldInterceptRequest(context, uri);
            if (webResourceResponse != null) {
                return webResourceResponse;
            }
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WebResourceResponse shouldInterceptRequest(Context context, WebResourceRequest request) {
        return shouldInterceptRequest(context, request.getUrl());
    }

    public WebResourceResponse shouldInterceptRequest(Context context, String url) {
        return shouldInterceptRequest(context, Uri.parse(url));
    }
}
