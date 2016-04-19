package dev.xesam.android.web.localresource;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
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
        String key = uri.toString();
        if (mapper.containsKey(key)) {
            String path = mapper.get(key);
            try {
                InputStream inputStream = context.getAssets().open(path);
                return new WebResourceResponse(null, Charset.defaultCharset().name(), inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void put(String key, String value) {
        mapper.put(key, value);
    }
}
