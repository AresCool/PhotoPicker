package com.luosifan.photopicker;

import android.app.Activity;

import com.luosifan.photopicker.picker.Load;

/**
 * Created by wzfu on 2016/5/10.
 */
public class PhotoPicker {

    public static PhotoPicker mSingleton = null;
    public final Activity mContext;

    public PhotoPicker(Activity context) {
        this.mContext = context;
    }

    public static PhotoPicker with(Activity context){
        if(mSingleton == null){
            synchronized (PhotoPicker.class){
                if(mSingleton == null){
                    mSingleton = new Contractor(context).build();
                }
            }
        }
        return mSingleton;
    }

    public Load load(){
        return new Load();
    }

    private static class Contractor {
        private final Activity mContext;

        public Contractor(Activity context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.mContext = context;
        }

        public PhotoPicker build() {
            return new PhotoPicker(mContext);
        }
    }
}
