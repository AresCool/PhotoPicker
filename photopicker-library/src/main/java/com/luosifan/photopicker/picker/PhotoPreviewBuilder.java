package com.luosifan.photopicker.picker;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.luosifan.photopicker.PhotoPicker;
import com.luosifan.photopicker.R;

import java.util.ArrayList;

/**
 * Created by wzfu on 16/5/21.
 */
public class PhotoPreviewBuilder extends Builder {

    public Class<? extends PreviewBaseActivity> previewPager;
    public int currentItem;
    public ArrayList<String> paths;

    public PhotoPreviewBuilder currentItem(int currentItem) {
        this.currentItem = currentItem;
        return this;
    }

    public PhotoPreviewBuilder paths(ArrayList<String> paths) {
        this.paths = paths;
        return this;
    }

    public PhotoPreviewBuilder previewPage(Class<? extends PreviewBaseActivity> previewPager) {
        this.previewPager = previewPager;
        return this;
    }

    @Override
    public void start(Activity aty, int enterAnim, int exitAnim) {

        if(!hasPermission(aty)) {
            Toast.makeText(aty, R.string.error_no_permission, Toast.LENGTH_SHORT).show();
            return;
        }

        aty.startActivityForResult(createIntent(aty), PhotoPicker.REQUEST_PREVIEW);
        overridePendingTransition(aty, enterAnim, exitAnim);
    }

    @Override
    public void start(Fragment fragment, int enterAnim, int exitAnim) {
        if(!hasPermission(fragment.getActivity())) {
            Toast.makeText(fragment.getActivity(), R.string.error_no_permission, Toast.LENGTH_SHORT).show();
            return;
        }

        fragment.startActivityForResult(createIntent(fragment.getActivity()), PhotoPicker.REQUEST_PREVIEW);
        overridePendingTransition(fragment.getActivity(), enterAnim, exitAnim);
    }

    @Override
    protected Intent createIntent(Activity aty) {
        Intent intent = new Intent(aty, previewPager);
        intent.putExtra(PreviewBaseActivity.CURRENT_ITEM, currentItem);
        intent.putStringArrayListExtra(PreviewBaseActivity.PHOTO_PATHS, paths);
        return intent;
    }
}
