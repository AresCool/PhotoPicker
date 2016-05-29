package com.luosifan.photopicker.picker;

import android.app.Activity;
import android.content.Intent;

import com.luosifan.photopicker.MultiImageSelectorActivity;
import com.luosifan.photopicker.PhotoPicker;

/**
 * Created by wzfu on 16/5/21.
 */
public class PhotoSelectBuilder extends Builder{

    protected PickerParams params;

    public PhotoSelectBuilder(PickerParams params) {
        this.params = params;
        if(params.filter == null){
            params.filter = new PhotoFilter();
        }
    }

    @Override
    protected Intent createIntent(Activity aty) {
        Intent intent = new Intent(aty, MultiImageSelectorActivity.class);
        intent.putExtra(PhotoPicker.PARAMS_PICKER, params);
        return intent;
    }
}
