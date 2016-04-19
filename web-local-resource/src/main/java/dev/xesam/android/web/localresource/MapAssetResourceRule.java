package dev.xesam.android.web.localresource;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xesamguo@gmail.com on 16-4-18.
 */
public class MapAssetResourceRule implements ResourceRule {
    private Map<String, String> mapper = new HashMap<>();

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public WebResourceResponse shouldInterceptRequest(Context context, Uri uri) {
        return null;
    }

    public void put(String key, String value) {

    }
}
