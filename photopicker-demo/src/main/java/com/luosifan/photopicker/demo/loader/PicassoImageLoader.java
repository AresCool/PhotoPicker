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
import com.luosifan.photopicker.OnPhotoClickListener;
import com.luosifan.photopicker.view.GFImageView;
import com.luosifan.photopicker.view.PinchImageView;
import com.squareup.picasso.Picasso;

import java.io.File;


/**
 * Desction:
 * Author:pengjianbo
 * Date:15/12/1 下午10:26
 * Updated by wzfu on 2016/5/22.
 */
public class PicassoImageLoader extends ImageLoader {

    private Bitmap.Config mConfig;

    public PicassoImageLoader() {
        this(Bitmap.Config.RGB_565);
    }

    public PicassoImageLoader(Bitmap.Config config) {
        this.mConfig = config;
    }

    @Override
    public void displayImage(Context mCxt, String path, GFImageView imageView, int tagId, int placeholderResId, int errorResId, int width, int height) {

        Picasso.with(mCxt)
                .load(new File(path))
                .placeholder(placeholderResId)
                .error(errorResId)
                .config(mConfig)
                .resize(width, height)
                .centerCrop()
                .tag(tagId)
                .into(imageView);
    }

    @Override
    public View displayPreview(Context mCxt, String path, int placeholderResId, int errorResId, OnPhotoClickListener listener) {

        PinchImageView pinchImageView = new PinchImageView(mCxt);

        Picasso.with(mCxt)
                .load(new File(path))
                .placeholder(placeholderResId)
                .error(errorResId)
                .into(pinchImageView);

        return pinchImageView;
    }

    @Override
    public void clearMemoryCache() {
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState, int tagId) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
            Picasso.with(view.getContext()).pauseTag(tagId);
        } else {
            Picasso.with(view.getContext()).resumeTag(tagId);
        }
    }
}
