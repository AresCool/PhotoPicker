package com.luosifan.photopicker.picker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzfu on 16/5/29.
 */
public class PreviewParams implements java.io.Serializable{

    public PickerTheme theme;

    public int currentItem;

    public ArrayList<String> paths;

    public Class<? extends PagerFragment> previewFragmentClass;
}
