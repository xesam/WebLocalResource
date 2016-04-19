package dev.xesam.android.web.localresource;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by xesamguo@gmail.com on 16-4-18.
 */
public class UrlAssetResourceRule implements ResourceRule {
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
//                String filepath = path.replace()
                InputStream inputStream = context.getAssets().open(path);
                return new WebResourceResponse(null, Charset.defaultCharset().name(), inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
