package com.luosifan.photopicker.picker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.luosifan.photopicker.PhotoPicker;
import com.luosifan.photopicker.R;

/**
 * Created by wzfu on 16/5/21.
 */
public abstract class Builder {

    protected PickerParams params;

    public Builder(PickerParams params) {
        this.params = params;
    }

    public void start(Fragment fragment){
        start(fragment, 0, 0);
    }

    public void start(Fragment fragment, int enterAnim, int exitAnim){

        if(!hasPermission(fragment.getActivity())) {
            Toast.makeText(fragment.getActivity(), R.string.error_no_permission, Toast.LENGTH_SHORT).show();
            return;
        }

        fragment.startActivityForResult(createIntent(fragment.getActivity()), PhotoPicker.REQUEST_SELECTED);
        overridePendingTransition(fragment.getActivity(), enterAnim, exitAnim);
    }

    public void start(Activity activity) {
        start(activity, 0, 0);
    }

    public void start(Activity aty, int enterAnim, int exitAnim) {

        if(!hasPermission(aty)) {
            Toast.makeText(aty, R.string.error_no_permission, Toast.LENGTH_SHORT).show();
            return;
        }

        aty.startActivityForResult(createIntent(aty), PhotoPicker.REQUEST_SELECTED);
        overridePendingTransition(aty, enterAnim, exitAnim);
    }

    private void overridePendingTransition(Activity aty, int enterAnim, int exitAnim){
        if(enterAnim != 0 || exitAnim != 0){
            aty.overridePendingTransition(enterAnim, exitAnim);
        }
    }

    private boolean hasPermission(Context mContext) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            // Permission was added in API Level 16
            return ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private Intent createIntent(Context mContext){
        Intent intent = new Intent(mContext, intentClass());
        intent.putExtra(PhotoPicker.PARAMS, params);
        return intent;
    }

    public abstract Class<? extends Activity> intentClass();
}