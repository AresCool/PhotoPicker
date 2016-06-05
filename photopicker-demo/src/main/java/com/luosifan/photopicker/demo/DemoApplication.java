package com.luosifan.photopicker.demo;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.x;

/**
 * Created by wzfu on 16/5/22.
 */
public class DemoApplication extends Application {

    private static DemoApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

    }

    public static DemoApplication getInstance() {
        return mInstance;
    }
}