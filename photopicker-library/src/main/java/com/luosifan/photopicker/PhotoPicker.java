package com.luosifan.photopicker;

import com.luosifan.photopicker.picker.Load;
import com.luosifan.photopicker.picker.PhotoPreviewBuilder;
import com.luosifan.photopicker.picker.PickerParams;
import com.luosifan.photopicker.picker.PickerTheme;

/**
 * Created by wzfu on 16/5/21.
 */
public class PhotoPicker {

    public static final int REQUEST_SELECTED = 201;
    public static final int REQUEST_PREVIEW = 216;

    public static final String PARAMS_PICKER = "photo_picker_params";
    public static final String PATHS = "photo_picker_paths";

    /** Result data set，ArrayList&lt;String&gt;*/
    public static final String EXTRA_RESULT = "select_result";

    private Class<? extends ImageLoader> imageLoaderClass;
    private PickerTheme theme;

    public PhotoPicker(Class<? extends ImageLoader> imageLoaderClass) {
        this.imageLoaderClass = imageLoaderClass;
    }

    public static PhotoPicker with(Class<? extends ImageLoader> imageLoaderClass) {
        if (imageLoaderClass == null) {
            throw new IllegalArgumentException("ImageLoader must not be null.");
        }
        return new PhotoPicker(imageLoaderClass);
    }

    public PhotoPicker theme(PickerTheme theme) {
        this.theme = theme;
        return this;
    }

    public Load load() {
        return new Load(imageLoaderClass, theme);
    }

    public PhotoPreviewBuilder preview() {
        return new PhotoPreviewBuilder(imageLoaderClass, theme);
    }
}
