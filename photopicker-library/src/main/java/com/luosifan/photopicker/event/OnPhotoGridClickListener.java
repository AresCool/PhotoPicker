package com.luosifan.photopicker.event;

import com.luosifan.photopicker.bean.Image;

/**
 * Created by wzfu on 2016/5/25.
 */
public interface OnPhotoGridClickListener {
    void onCameraClick();
    void onPhotoClick(Image image, int positon);
}
