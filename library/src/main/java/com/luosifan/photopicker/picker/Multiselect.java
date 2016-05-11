package com.luosifan.photopicker.picker;

import com.luosifan.photopicker.model.PickerParams;

/**
 * 多选
 * Created by wzfu on 16/5/10.
 */
public class Multiselect extends Builder {

    public Multiselect(PickerParams params, int enterAnim, int exitAnim) {
        super(params, enterAnim, exitAnim);
        pickerParams.isMultiPick = true;
        // 默认多选照片数量
        pickerParams.maxPickSize = 9;
    }

    public Multiselect maxPickSize(int maxPickSize){
        if(maxPickSize <= 1){
            throw new IllegalArgumentException("maxPickSize must not be > 1.");
        }
        pickerParams.maxPickSize = maxPickSize;
        return this;
    }
}
