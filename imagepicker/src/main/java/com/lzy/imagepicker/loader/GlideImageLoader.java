package com.lzy.imagepicker.loader;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.imagepicker.R;

import uk.co.senab.photoview.PhotoView;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧 Github地址：https://github.com/jeasonlzy0216
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class GlideImageLoader extends ImageLoader<ImageView, PhotoView> {

    @Override
    public ImageView onCreateGridItemView(Context mCxt) {
        return new ImageView(mCxt);
    }

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load("file://" + path)                  //设置图片路径
                .error(R.mipmap.default_image)           //设置错误图片
                .placeholder(R.mipmap.default_image)     //设置占位图片
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }
}
