package com.luosifan.photopicker.picker;

import com.luosifan.photopicker.utils.ImageLoader;

import java.util.ArrayList;

/**
 * 图片选择器参数
 * Created by wzfu on 16/5/21.
 */
public class PickerParams implements java.io.Serializable{

    public Class<? extends ImageLoader> imageLoaderClass;

    public PickerTheme theme;

    public PhotoFilter filter;

    public boolean showCamera;

    // 选择模式
    public SelectMode mode;

    // 默认最大选择照片数量
    public int maxPickSize = 9;

    // 选择图片列表默认列数
    public int gridColumns = 3;

    // 已选照片地址
    public ArrayList<String> selectedPaths;

    // 当前选中照片的下标, 预览照片时使用
    public int currentIndex;
}
