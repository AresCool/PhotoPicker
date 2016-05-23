package com.luosifan.photopicker.adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luosifan.photopicker.ImageLoader;
import com.luosifan.photopicker.R;
import com.luosifan.photopicker.utils.ScreenUtils;
import com.luosifan.photopicker.view.PhotoView;
import com.luosifan.photopicker.view.phtotoview.PhotoViewAttacher;

import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzfu on 16/5/23.
 */
public class PhotoPreviewAdapter extends PagerAdapter {

    private Context mCxt;
    private List<String> paths = new ArrayList<>();
    private ImageLoader imageLoader;
    public PhotoViewClickListener clickListener;
    private LayoutInflater inflater;

    Point point;

    public interface PhotoViewClickListener{
        void OnPhotoTapListener(View view, float v, float v1);
    }

    public void setPhotoViewClickListener(PhotoViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public PhotoPreviewAdapter(Context mCxt, List<String> paths, ImageLoader imageLoader) {
        this.mCxt = mCxt;
        this.paths = paths;
        this.imageLoader = imageLoader;
        this.point = ScreenUtils.getScreenSize(mCxt);
        this.inflater = LayoutInflater.from(mCxt);
    }

    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {

        PhotoView photoView = new PhotoView(mCxt);

        if(clickListener != null) {
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    clickListener.OnPhotoTapListener(view, x, y);
                }
            });
        }

        if(imageLoader != null){
            imageLoader.displayImage(mCxt, paths.get(position), photoView,
                    R.id.photopicker_item_tag_id,
                    R.drawable.default_error,
                    R.drawable.default_error,
                    point.x / 2,
                    point.y / 2);
        }

        container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
//
//    @Override
//    public int getItemPosition (Object object) {
//        return POSITION_NONE;
//    }
}
