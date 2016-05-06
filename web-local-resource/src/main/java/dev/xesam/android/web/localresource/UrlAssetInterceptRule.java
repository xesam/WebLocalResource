package dev.xesam.android.web.localresource;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by xesamguo@gmail.com on 16-4-18.
 */
public class UrlAssetInterceptRule implements InterceptRule {
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public WebResourceResponse shouldInterceptRequest(Context context, Uri uri) {
        String host = uri.getHost();
        if (!TextUtils.isEmpty(host)) {
            try {
                String path = uri.getPath();
                if (path.startsWith("/")) {
                    path = path.substring(1);
                }
                InputStream inputStream = context.getAssets().open(path);
                if (LocalResourceInterceptor.DEBUG) {
                    Log.d("intercept hit", uri.toString() + " --> " + path);
                }
                return new WebResourceResponse(null, Charset.defaultCharset().name(), inputStream);
            } catch (IOException e) {
                if (LocalResourceInterceptor.DEBUG) {
                    Log.d("intercept not found", uri.toString());
                }
            }
        }
        return null;
    }
}
