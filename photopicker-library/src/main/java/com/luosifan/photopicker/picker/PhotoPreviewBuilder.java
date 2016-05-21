package com.luosifan.photopicker.picker;

import android.app.Activity;

/**
 * Created by wzfu on 16/5/21.
 */
public class PhotoPreviewBuilder extends Builder{

    public PhotoPreviewBuilder(PickerParams params) {
        super(params);
    }

    @Override
    public Class<? extends Activity> intentClass() {
        return null;
    }
}
