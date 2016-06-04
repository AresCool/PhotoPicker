package com.luosifan.photopicker.demo.loader;

import android.content.Context;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.luosifan.photopicker.picker.PhotoPickerImageLoader;

import me.relex.photodraweeview.PhotoDraweeView;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by eddie on 16/6/4.
 */
public class CustomLoader extends PhotoPickerImageLoader<SimpleDraweeView, PhotoDraweeView> {

    @Override
    protected SimpleDraweeView onCreateGridItemView(Context context) {
        return null;
    }

    @Override
    protected void loadGridItemView(SimpleDraweeView view, String imagePath, int tag, int width, int height) {

    }

    @Override
    public void resumeRequests(Context mCxt, int tag) {

    }

    @Override
    public void pauseRequests(Context mCxt, int tag) {

    }

    @Override
    protected PhotoDraweeView onCreatePreviewItemView(Context context) {
        return null;
    }

    @Override
    protected void loadPreviewItemView(PhotoDraweeView view, String imagePath, int width, int height) {

    }
}
