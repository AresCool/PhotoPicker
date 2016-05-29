package com.luosifan.photopicker.demo.preview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.luosifan.photopicker.demo.R;
import com.luosifan.photopicker.demo.widget.PinchImageView;
import com.luosifan.photopicker.demo.widget.PinchImageViewPager;
import com.luosifan.photopicker.picker.PagerFragment;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by wzfu on 16/5/29.
 */
public class PreviewFragment extends PagerFragment implements PinchImageViewPager.OnPageChangeListener{

    private DisplayMetrics displayMetrics;
    PinchImageViewPager pager;
    final LinkedList<PinchImageView> viewCache = new LinkedList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_preview;
    }

    @Override
    protected void initView(View view) {

        displayMetrics = getResources().getDisplayMetrics();

        pager = (PinchImageViewPager) view.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(5);
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return paths.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PinchImageView piv;
                if (viewCache.size() > 0) {
                    piv = viewCache.remove();
                    piv.reset();
                } else {
                    piv = new PinchImageView(aty);
                }
                String path = paths.get(position);
                Glide.with(aty).load(new File(path))
                        .skipMemoryCache(false)
//                        .resize(displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 2)
                        .into(piv);
                container.addView(piv);
                return piv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                PinchImageView piv = (PinchImageView) object;
                container.removeView(piv);
                viewCache.add(piv);
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                PinchImageView piv = (PinchImageView) object;
                String path = paths.get(position);
                Picasso.with(aty).load(new File(path)).into(piv);
                pager.setMainPinchImageView(piv);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
