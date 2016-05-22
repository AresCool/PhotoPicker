package com.luosifan.photopicker.picker;

import android.app.Activity;

import com.luosifan.photopicker.MultiImageSelectorActivity;

/**
 * Created by wzfu on 16/5/21.
 */
public class PhotoSelectBuilder extends Builder{

    public PhotoSelectBuilder(PickerParams params) {
        super(params);

        if(params.filter == null){
            params.filter = new PhotoFilter();
        }
    }

    @Override
    public Class<? extends Activity> intentClass() {
        return MultiImageSelectorActivity.class;
    }
}
