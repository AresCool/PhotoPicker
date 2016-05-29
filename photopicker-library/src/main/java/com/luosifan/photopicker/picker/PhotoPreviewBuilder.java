package com.luosifan.photopicker.picker;

import android.app.Activity;
import android.content.Intent;

import com.luosifan.photopicker.PhotoPicker;
import com.luosifan.photopicker.PhotoPreviewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzfu on 16/5/21.
 */
public class PhotoPreviewBuilder extends Builder {

    private PreviewParams params;

    public PhotoPreviewBuilder() {
        this.params = new PreviewParams();
    }

    public PhotoPreviewBuilder theme(PickerTheme theme) {
        this.params.theme = theme;
        return this;
    }

    public PhotoPreviewBuilder currentItem(int currentItem) {
        this.params.currentItem = currentItem;
        return this;
    }

    public PhotoPreviewBuilder paths(ArrayList<String> paths) {
        this.params.paths = paths;
        return this;
    }

    public PhotoPreviewBuilder previewPage(Class<? extends PagerFragment> previewFragmentClass) {
        this.params.previewFragmentClass = previewFragmentClass;
        return this;
    }

    @Override
    protected Intent createIntent(Activity aty) {
        Intent intent = new Intent(aty, PhotoPreviewActivity.class);
        intent.putExtra(PhotoPicker.PARAMS_PREVIEW, params);
        return intent;
    }
}
