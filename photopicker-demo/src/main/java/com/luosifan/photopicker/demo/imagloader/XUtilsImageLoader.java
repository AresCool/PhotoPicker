package com.luosifan.photopicker.demo.imagloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.luosifan.photopicker.PhotoPickerImageLoader;
import com.luosifan.photopicker.demo.DemoApplication;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by wzfu on 16/6/5.
 */
public class XUtilsImageLoader extends PhotoPickerImageLoader<ImageView, PhotoView> {

    private int holderResId = com.luosifan.photopicker.R.drawable.default_error;
    private Bitmap.Config mImageConfig;

    public XUtilsImageLoader() {
        x.Ext.init(DemoApplication.getInstance());
        this.mImageConfig = Bitmap.Config.RGB_565;
    }

    @Override
    public ImageView onCreateGridItemView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return imageView;
    }

    @Override
    public void loadGridItemView(ImageView view, String imagePath, int tag, int width, int height) {
        ImageOptions options = new ImageOptions.Builder()
                .setLoadingDrawableId(holderResId)
                .setFailureDrawableId(holderResId)
                .setConfig(mImageConfig)
                .setSize(width, height)
                .setCrop(true)
                .setUseMemCache(true)
                .build();
        x.image().bind(view, "file://" + imagePath, options);
    }

    @Override
    public void resumeRequests(Context mCxt, int tag) {

    }

    @Override
    public void pauseRequests(Context mCxt, int tag) {

    }

    @Override
    protected PhotoView onCreatePreviewItemView(Context context) {
        PhotoView photoView = new PhotoView(context);
        return photoView;
    }

    @Override
    protected void loadPreviewItemView(PhotoView view, String imagePath, int width, int height) {
        ImageOptions options = new ImageOptions.Builder()
                .setConfig(mImageConfig)
                .setSize(width, height)
                .setUseMemCache(true)
                .build();
        x.image().bind(view, "file://" + imagePath, options);
    }
}
