package com.luosifan.photopicker;

import android.app.Activity;

import com.luosifan.photopicker.picker.Load;

/**
 * Created by wzfu on 2016/5/10.
 */
public class PhotoPicker {

    public static final int REQUEST_PHOTO_CODE = 1;

    public static PhotoPicker mSingleton = null;
    public final Activity aty;
    public final ImageLoader imageLoader;

    public PhotoPicker(Activity context, ImageLoader imageLoader) {
        this.aty = context;
        this.imageLoader = imageLoader;
    }

    public static PhotoPicker with(Activity context, ImageLoader imageLoader){
        if(mSingleton == null){
            synchronized (PhotoPicker.class){
                if(mSingleton == null){
                    mSingleton = new Contractor(context, imageLoader).build();
                }
            }
        }
        return mSingleton;
    }

    public Load load(){
        return new Load();
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }

    private static class Contractor {
        private final Activity mContext;
        private final ImageLoader imageLoader;

        public Contractor(Activity context, ImageLoader imageLoader) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            if(imageLoader == null){
                throw new IllegalArgumentException("ImageLoader must not be null.");
            }
            this.mContext = context;
            this.imageLoader = imageLoader;
        }

        public PhotoPicker build() {
            return new PhotoPicker(mContext, imageLoader);
        }
    }


}
