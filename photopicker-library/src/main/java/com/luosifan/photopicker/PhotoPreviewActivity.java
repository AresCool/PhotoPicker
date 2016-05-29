package com.luosifan.photopicker;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.luosifan.photopicker.picker.PreviewBaseActivity;
import com.luosifan.photopicker.view.FixedViewPager;

import java.util.List;


/**
 * Created by eddie on 16/5/29.
 */
public class PhotoPreviewActivity extends PreviewBaseActivity {

    FixedViewPager mViewPager;
    PreviewAdapter adapter;

    @Override
    protected void initWidget() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new PreviewAdapter(this, paths);
        mViewPager = (FixedViewPager) findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentItem);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateTitle();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        updateTitle();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_preview;
    }

    @Override
    public void updateTitle() {
        getSupportActionBar().setTitle(getString(R.string.action_string_preview,
                mViewPager.getCurrentItem() + 1, paths.size()));
    }

    @Override
    public void deleteImage() {
        final int index = mViewPager.getCurrentItem();

        if (paths.size() == 1) {
            new AlertDialog.Builder(this)
                    .setMessage(getString(R.string.tip_delete))
                    .setPositiveButton(getString(R.string.photo_delete_ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            paths.remove(index);
                            back();
                        }
                    })
                    .setNegativeButton(getString(R.string.photo_delete_cancel), null)
                    .show();

        } else {
            paths.remove(index);
            adapter.notifyDataSetChanged();
        }
    }

    private class PreviewAdapter extends PagerAdapter {

        private Context mCxt;
        private List<String> paths;

        public PreviewAdapter(Context mCxt, List<String> paths) {
            this.mCxt = mCxt;
            this.paths = paths;
        }

        @Override
        public int getCount() {
            return paths.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {

            View view = null;

            if(imageLoader != null) {
                view = imageLoader.instantiateItem(mCxt, paths.get(position));
            }

            if(view != null) {
                container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
            }

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition (Object object) { return POSITION_NONE; }
    }
}
