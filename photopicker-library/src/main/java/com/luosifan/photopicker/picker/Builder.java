package com.luosifan.photopicker.picker;

import android.app.Activity;
import android.support.v4.app.Fragment;

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

    }

    public void start(Activity activity){
        start(activity, 0, 0);
    }

    public void start(Activity activity, int enterAnim, int exitAnim){

    }

    public abstract Class<? extends Activity> intentClass();
}