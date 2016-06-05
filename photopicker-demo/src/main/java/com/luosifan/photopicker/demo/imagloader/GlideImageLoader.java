package com.luosifan.photopicker.demo.imagloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.luosifan.photopicker.PhotoPickerImageLoader;

import java.io.File;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by wzfu on 16/6/5.
 */
public class GlideImageLoader extends PhotoPickerImageLoader<ImageView, PhotoView>{

    private int holderResId = com.luosifan.photopicker.R.drawable.default_error;

    @Override
    public ImageView onCreateGridItemView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return imageView;
    }

    @Override
    public void loadGridItemView(ImageView view, String imagePath, final int tag, int width, int height) {

        Drawable holderDrawable = ContextCompat.getDrawable(view.getContext(), holderResId);

        Glide.with(view.getContext())
                .load("file://" + imagePath)
                .placeholder(holderDrawable)
                .error(holderDrawable)
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.NONE) //不缓存到SD卡
                .skipMemoryCache(false)
                .centerCrop()
                .crossFade()
                .into(new ImageViewTarget<GlideDrawable>(view) {
                    @Override
                    protected void setResource(GlideDrawable resource) {
                        view.setImageDrawable(resource);
                    }

                    @Override
                    public void setRequest(Request request) {
                        view.setTag(tag, request);
                    }

                    @Override
                    public Request getRequest() {
                        return (Request) view.getTag(tag);
                    }
                });
    }

    @Override
    public void resumeRequests(Context mCxt, int tag) {
        Glide.with(mCxt).resumeRequests();
    }

    @Override
    public void pauseRequests(Context mCxt, int tag) {
        Glide.with(mCxt).pauseRequests();
    }

    @Override
    protected PhotoView onCreatePreviewItemView(Context context) {
        PhotoView photoView = new PhotoView(context);
        return photoView;
    }

    @Override
    protected void loadPreviewItemView(PhotoView view, String imagePath, int width, int height) {
        Glide.with(view.getContext())
                .load(Uri.fromFile(new File(imagePath)))
                .override(width, height)
                .crossFade()
                .into(view);
    }
}
