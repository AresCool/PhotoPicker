package com.luosifan.photopicker.utils;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.content.CursorLoader;

import com.luosifan.photopicker.picker.PhotoFilter;

import static android.provider.MediaStore.MediaColumns.MIME_TYPE;

/**
 * Created by 黄东鲁 on 15/6/28.
 * Updated by wzfu on 16/5/22.
 */
public class PhotoDirectoryLoader extends CursorLoader {

    private final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media._ID};

    public PhotoDirectoryLoader(Context context, PhotoFilter filter) {
        super(context);

        setProjection(IMAGE_PROJECTION);
        setUri(Media.EXTERNAL_CONTENT_URI);
        setSortOrder(Media.DATE_ADDED + " DESC");

        setSelection("(" + MIME_TYPE + "=? or " + MIME_TYPE + "=? or " + MIME_TYPE + "=?" +
                (filter.showGif ? (" or " + MIME_TYPE + "=? ") : "") + ")" +
                filterCofing(filter));

        String[] selectionArgs;
        if (filter.showGif) {
            selectionArgs = new String[]{"image/jpeg", "image/png", "image/jpg", "image/gif"};
        } else {
            selectionArgs = new String[]{"image/jpeg", "image/png", "image/jpg"};
        }

        setSelectionArgs(selectionArgs);
    }

    private String filterCofing(PhotoFilter filter) {
        StringBuilder sb = new StringBuilder();
        // 图片最低宽度
        if (filter.minWidth > 0) {
            sb.append(" and ");
            sb.append(MediaStore.Images.Media.WIDTH + " >= " + filter.minWidth);
        }
        // 图片最低高低
        if (filter.minHeight > 0) {
            sb.append(" and ");
            sb.append(MediaStore.Images.Media.HEIGHT + " >= " + filter.minHeight);
        }
        if (filter.minSize > 0) {
            sb.append(" and ");
            sb.append(MediaStore.Images.Media.SIZE + " >= " + filter.minSize);
        }
        return sb.toString();
    }
}
