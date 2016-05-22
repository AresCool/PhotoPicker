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

package com.luosifan.photopicker.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.AbsListView;

import com.luosifan.photopicker.view.GFImageView;

import java.io.Serializable;


/**
 * Desction:imageloader抽象类，外部需要实现这个类去加载图片， GalleryFinal尽力减少对第三方库的依赖，所以这么干了
 * Author:pengjianbo
 * Date:15/10/10 下午5:27
 */
public interface ImageLoader extends Serializable{

    void displayImage(Context mCxt,
                      String path,
                      GFImageView imageView,
                      int tagId,
                      int placeholderResId,
                      int errorResId,
                      int width, int height);

    void clearMemoryCache();

    /**
     * 图片列表滑动监听
     * @param view
     * @param scrollState
     */

    // scrollState = SCROLL_STATE_TOUCH_SCROLL(1) 正在滚动
    // scrollState = SCROLL_STATE_FLING(2) 手指做了抛的动作（手指离开屏幕前，用力滑了一下）
    // scrollState = SCROLL_STATE_IDLE(0) 停止滚动

    void onScrollStateChanged(AbsListView view, int scrollState, int tag);
}
