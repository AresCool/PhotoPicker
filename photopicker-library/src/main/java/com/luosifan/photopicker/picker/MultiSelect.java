package com.luosifan.photopicker.picker;

import java.util.List;

/**
 * Created by wzfu on 16/5/21.
 */
public class MultiSelect extends PhotoSelectBuilder {

    public MultiSelect(PickerParams params) {
        super(params);
    }

    public MultiSelect selectedPaths(List<String> paths){
        super.params.selectedPaths = paths;
        return this;
    }

    public MultiSelect maxPickSize(int maxPickSize){
        if(maxPickSize < 1){
            throw new IllegalArgumentException("maxPickSize must not be > 0.");
        }
        super.params.maxPickSize = maxPickSize;
        return this;
    }
}
