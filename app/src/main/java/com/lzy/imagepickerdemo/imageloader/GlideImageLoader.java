package com.lzy.imagepickerdemo.imageloader;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cc.dagger.photopicker.loader.ImageLoader;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by wangzf on 2016/6/12.
 */
public class GlideImageLoader extends ImageLoader<ImageView, PhotoView> {

    @Override
    public ImageView onCreateGridItemView(Context mCxt) {
        return new ImageView(mCxt);
    }

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)
                .load("file://" + path)
                .placeholder(getPlaceholder())
                .error(getPlaceholder())
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.NONE) //不缓存到SD卡
                .skipMemoryCache(false)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    @Override
    public PhotoView onCreatePreviewItemView(Context mCxt) {
        return null;
    }

    @Override
    public void clearMemoryCache() {

    }
}
