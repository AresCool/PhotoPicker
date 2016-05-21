package com.luosifan.photopicker;

import com.luosifan.photopicker.picker.Load;
import com.luosifan.photopicker.picker.PhotoFilter;
import com.luosifan.photopicker.picker.PickerParams;
import com.luosifan.photopicker.picker.PickerTheme;
import com.luosifan.photopicker.utils.ImageLoader;

/**
 * Created by wzfu on 16/5/21.
 */
public class PhotoPicker {

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
}
