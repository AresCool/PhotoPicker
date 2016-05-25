/*
 * Copyright (C) 2014 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.luosifan.photopicker.demo.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.luosifan.photopicker.view.GFImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Desction:
 * Author:pengjianbo
 * Date:15/10/10 下午5:52
 * Updated by wzfu on 2016/5/22.
 */
public class UILImageLoader extends com.luosifan.photopicker.ImageLoader {

    private Bitmap.Config mImageConfig;

    public UILImageLoader() {
        this(Bitmap.Config.RGB_565);
    }

    public UILImageLoader(Bitmap.Config config) {
        this.mImageConfig = config;
    }


    @Override
    public void displayImage(Context mCxt, String path, GFImageView imageView, int tagId, int placeholderResId, int errorResId, int width, int height) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(false)
                .cacheInMemory(true)
                .bitmapConfig(mImageConfig)
                .showImageOnLoading(placeholderResId)
                .showImageOnFail(errorResId)
                .showImageOnFail(placeholderResId)
                .build();

        ImageSize imageSize = new ImageSize(width, height);

        ImageLoader.getInstance().displayImage("file://" + path, new ImageViewAware(imageView), options, imageSize, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                GFImageView gfImageView = (GFImageView) view;
                gfImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    GFImageView gfImageView = (GFImageView) view;
                    gfImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        }, null);
    }

    @Override
    public void pauseRequests(Context mCxt, int tagId) {
        ImageLoader.getInstance().pause();
    }

    @Override
    public void resumeRequests(Context mCxt, int tagId) {
        ImageLoader.getInstance().resume();
    }
}
