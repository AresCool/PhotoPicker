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
import android.view.View;
import android.widget.AbsListView;

import com.luosifan.photopicker.ImageLoader;
import com.luosifan.photopicker.view.GFImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import uk.co.senab.photoview.PhotoView;

/**
 * Desction:
 * Author:pengjianbo
 * Date:15/12/2 下午6:54
 * Updated by wzfu on 2016/5/22.
 */
public class XUtilsImageLoader extends ImageLoader {

    private Bitmap.Config mImageConfig;

    public XUtilsImageLoader() {
        this(Bitmap.Config.RGB_565);
    }

    public XUtilsImageLoader(Bitmap.Config config) {
        this.mImageConfig = config;
    }

    @Override
    public void displayImage(Context mCxt, String path, GFImageView imageView, int tagId, int placeholderResId, int errorResId, int width, int height) {
        ImageOptions options = new ImageOptions.Builder()
                .setLoadingDrawableId(placeholderResId)
                .setFailureDrawableId(errorResId)
                .setConfig(mImageConfig)
                .setSize(width, height)
                .setCrop(true)
                .setUseMemCache(true)
                .build();
        x.image().bind(imageView, "file://" + path, options);
    }

    @Override
    public View instantiateItem(Context context, String imagePath, int imageWidth, int imageHeight) {
        PhotoView photoView = new PhotoView(context);
        ImageOptions options = new ImageOptions.Builder()
                .setConfig(mImageConfig)
                .setSize(imageWidth, imageHeight)
                .setUseMemCache(true)
                .build();
        x.image().bind(photoView, "file://" + imagePath, options);
        return photoView;
    }

    @Override
    public void clearMemoryCache() {
    }
}
