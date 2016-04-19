package dev.xesam.android.web.localresource;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebResourceResponse;

/**
 * the rule to get local resource
 * Created by xesamguo@gmail.com on 16-4-18.
 */
public interface ResourceRule {
    WebResourceResponse shouldInterceptRequest(Context context, Uri uri);
}
