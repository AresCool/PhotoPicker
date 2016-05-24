package com.luosifan.photopicker.adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.luosifan.photopicker.ImageLoader;
import com.luosifan.photopicker.OnPhotoClickListener;
import com.luosifan.photopicker.R;
import com.luosifan.photopicker.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzfu on 16/5/23.
 */
public class PhotoPreviewAdapter extends PagerAdapter {

    private Context mCxt;
    private List<String> paths = new ArrayList<>();
    private ImageLoader imageLoader;
    public OnPhotoClickListener clickListener;

    Point point;

    public void setPhotoViewClickListener(OnPhotoClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public PhotoPreviewAdapter(Context mCxt, List<String> paths, ImageLoader imageLoader) {
        this.mCxt = mCxt;
        this.paths = paths;
        this.imageLoader = imageLoader;
        this.point = ScreenUtils.getScreenSize(mCxt);
    }

    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = imageLoader.displayPreview(mCxt, paths.get(position),
                R.drawable.default_error,
                R.drawable.default_error,
                clickListener);
        if(view != null) {
            container.addView(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition (Object object) {
        return POSITION_NONE;
    }
}
