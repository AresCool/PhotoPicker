package com.luosifan.photopicker.picker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luosifan.photopicker.PhotoPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzfu on 16/5/29.
 */
public abstract class PagerFragment extends Fragment {

    public static final String CURRENT_ITEM = "photo_preview_current_item";
    public static final String PHOTO_PATHS = "photo_preview_paths";

    public Activity aty;
    public int currentItem;
    public List<String> paths;
    public List<String> tmpPaths = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        aty = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            currentItem = getArguments().getInt(CURRENT_ITEM);
            paths = getArguments().getStringArrayList(PHOTO_PATHS);
        }

        if(paths == null) {
            paths = new ArrayList<>();
        }

        tmpPaths.addAll(paths);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        return view;
    }

    // 获取布局文件Id
    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    public int getResultCode(){
        return tmpPaths.size() == paths.size() ? Activity.RESULT_CANCELED : PhotoPicker.REQUEST_PREVIEW;
    }
}
