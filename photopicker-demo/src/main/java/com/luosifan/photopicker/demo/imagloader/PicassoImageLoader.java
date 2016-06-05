package com.luosifan.photopicker.demo.imagloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.luosifan.photopicker.PhotoPickerImageLoader;
import com.squareup.picasso.Picasso;

import java.io.File;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by wzfu on 16/6/5.
 */
public class PicassoImageLoader extends PhotoPickerImageLoader<ImageView, PhotoView> {

    private int holderResId;
    private Bitmap.Config mConfig;

    public PicassoImageLoader() {
        this.holderResId = com.luosifan.photopicker.R.drawable.default_error;
        this.mConfig = Bitmap.Config.ARGB_8888;
    }

    @Override
    public ImageView onCreateGridItemView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return imageView;
    }

    @Override
    public void loadGridItemView(ImageView view, String imagePath, int tag, int width, int height) {
        Picasso.with(view.getContext())
                .load(new File(imagePath))
                .placeholder(holderResId)
                .error(holderResId)
                .config(mConfig)
                .resize(width, height)
                .centerCrop()
                .tag(tag)
                .into(view);
    }

    @Override
    public void resumeRequests(Context mCxt, int tag) {
        Picasso.with(mCxt).resumeTag(tag);
    }

    @Override
    public void pauseRequests(Context mCxt, int tag) {
        Picasso.with(mCxt).pauseTag(tag);
    }

    @Override
    protected PhotoView onCreatePreviewItemView(Context context) {
        PhotoView photoView = new PhotoView(context);
        return photoView;
    }

    @Override
    protected void loadPreviewItemView(PhotoView view, String imagePath, int width, int height) {
        Picasso.with(view.getContext())
                .load(Uri.fromFile(new File(imagePath)))
                .resize(width, height)
                .into(view);
    }
}
