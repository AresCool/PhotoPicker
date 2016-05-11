package com.luosifan.photopicker.picker;

import android.content.Intent;

import com.luosifan.photopicker.PhotoPicker;
import com.luosifan.photopicker.PhotoPickerActivity;
import com.luosifan.photopicker.model.PickerParams;

/**
 * Created by wzfu on 16/5/10.
 */
public class Builder {

    protected PickerParams pickerParams;
    private int enterAnim;
    private int exitAnim;

    public Builder(PickerParams params, int enterAnim, int exitAnim) {
        this.pickerParams = params;
        this.enterAnim = enterAnim;
        this.exitAnim = exitAnim;
    }

    public void build(){

        Intent intent = new Intent();
        intent.setClass(PhotoPicker.mSingleton.aty, PhotoPickerActivity.class);
        PhotoPicker.mSingleton.aty.startActivity(intent);

        if(enterAnim != 0 || exitAnim != 0){
            PhotoPicker.mSingleton.aty.overridePendingTransition(enterAnim, exitAnim);
        }
    }
}
