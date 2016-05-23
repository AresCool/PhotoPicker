package com.luosifan.photopicker;

import com.luosifan.photopicker.picker.Load;
import com.luosifan.photopicker.picker.PickerParams;
import com.luosifan.photopicker.picker.PickerTheme;
import com.luosifan.photopicker.picker.Preview;

/**
 * Created by wzfu on 16/5/21.
 */
public class PhotoPicker {

    public static final int REQUEST_SELECTED = 201;
    public static final int REQUEST_PREVIEW = 216;

    public static final String PARAMS = "photo_picker_params";

    /** Result data set，ArrayList&lt;String&gt;*/
    public static final String EXTRA_RESULT = "select_result";

    private PickerParams params;

    public PhotoPicker(Class<? extends ImageLoader> imageLoaderClass) {
        params = new PickerParams();
        params.imageLoaderClass = imageLoaderClass;
    }

    public static PhotoPicker with(Class<? extends ImageLoader> imageLoaderClass) {

        if (imageLoaderClass == null) {
            throw new IllegalArgumentException("ImageLoader must not be null.");
        }

        return new PhotoPicker(imageLoaderClass);
    }

    public PhotoPicker theme(PickerTheme theme){
        this.params.theme = theme;
        return this;
    }

    public Load load(){
        return new Load(params);
    }

    public Preview preview(){
        return new Preview(params);
    }
}
