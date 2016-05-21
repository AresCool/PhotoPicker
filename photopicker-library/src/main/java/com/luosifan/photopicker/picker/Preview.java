package com.luosifan.photopicker.picker;

import java.util.List;

/**
 * Created by wzfu on 16/5/21.
 */
public class Preview extends PhotoPreviewBuilder{

    public Preview(PickerParams params) {
        super(params);
    }

    public Preview paths(List<String> selectedPaths){
        super.params.selectedPaths = selectedPaths;
        return this;
    }

    public Preview currentIndex(int index){
        this.params.currentIndex = index;
        return this;
    }

}
