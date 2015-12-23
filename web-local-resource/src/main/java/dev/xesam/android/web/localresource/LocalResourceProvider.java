package dev.xesam.android.web.localresource;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by xesamguo@gmail.com on 10/16/15.
 */
public class LocalResourceProvider extends ContentProvider {

    public static final String AUTHORITIES = "dev.xesam.android.web";

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {

//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
//
//        }

        Log.e("ParcelFileDescriptor", "openFile:" + uri.toString());
        AssetFileDescriptor assetFileDescriptor = null;
        ParcelFileDescriptor parcel = null;

        try {
            if (uri.toString().endsWith(".css")) {
                assetFileDescriptor = getContext().getAssets().openFd("local.css");
            } else if (uri.toString().endsWith(".js")) {
                assetFileDescriptor = getContext().getAssets().openFd("local.js");
            } else if (uri.toString().endsWith(".png")) {
                File file = new File(getContext().getExternalFilesDir(null), "local.png");
                System.out.println(file);
                return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
//                assetFileDescriptor = getContext().getAssets().openFd("local.png");
            }
            if (assetFileDescriptor != null) {
                parcel = assetFileDescriptor.getParcelFileDescriptor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parcel;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] as) {
        throw new UnsupportedOperationException("Not supported by this provider");
    }

    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not supported by this provider");
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentvalues) {
        throw new UnsupportedOperationException("Not supported by this provider");
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] as, String s, String[] as1, String s1) {
        throw new UnsupportedOperationException("Not supported by this provider");
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentvalues, String s, String[] as) {
        throw new UnsupportedOperationException("Not supported by this provider");
    }
}
