package dev.xesam.android.web.localresource;

import android.net.Uri;

/**
 * Created by FanSS on 2015/12/21.
 */
public final class LocalResourceUtils {

    public static String getAssetsFilePath(Uri uri) {
        return uri.getPath().replaceFirst("/", "");
    }
}
