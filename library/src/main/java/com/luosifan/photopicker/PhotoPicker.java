package com.luosifan.photopicker;

import android.content.Context;

/**
 * Created by wzfu on 2016/5/10.
 */
public class PhotoPicker {

    public static PhotoPicker mSingleton = null;
    public final Context mContext;

    public PhotoPicker(Context context) {
        this.mContext = context;
    }

    public static PhotoPicker with(Context context){
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
        private final Context mContext;

        public Contractor(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.mContext = context.getApplicationContext();
        }

        public PhotoPicker build() {
            return new PhotoPicker(mContext);
        }
    }
}
