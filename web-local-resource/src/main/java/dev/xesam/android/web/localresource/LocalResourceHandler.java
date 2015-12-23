package dev.xesam.android.web.localresource;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xesamguo@gmail.com on 10/16/15.
 */
public final class LocalResourceHandler {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public WebResourceResponse shouldInterceptRequest(Context context, Uri uri) {
        String host = uri.getHost();
        if (!TextUtils.isEmpty(host) && host.startsWith(LocalResourceProvider.AUTHORITIES)) {
            try {
                String fileName = LocalResourceUtils.getAssetsFilePath(uri);
                InputStream inputStream = context.getAssets().open(fileName);
                return new WebResourceResponse(null, "utf-8", inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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
