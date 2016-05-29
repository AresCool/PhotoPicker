package com.luosifan.photopicker.demo.preview;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.foamtrace.photopicker.widget.ViewPagerFixed;
import com.luosifan.photopicker.demo.R;
import com.luosifan.photopicker.picker.PreviewBaseActivity;

import java.io.File;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by eddie on 16/5/29.
 */
public class PreviewActivity extends PreviewBaseActivity {

    ViewPagerFixed mViewPager;
    PreviewAdapter adapter;

    @Override
    protected void initWidget() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        Toolbar toolbar = (Toolbar) findViewById(com.luosifan.photopicker.R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new PreviewAdapter(this, paths);
        mViewPager = (ViewPagerFixed) findViewById(R.id.viewPager);
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
                    .setMessage("确定要删除吗?")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            paths.remove(index);
                            back();
                        }
                    })
                    .setNegativeButton("取消", null)
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
            PhotoView photoView = new PhotoView(container.getContext());

            Glide.with(mCxt)
                .load(Uri.fromFile(new File(paths.get(position))))
                .error(com.foamtrace.photopicker.R.mipmap.default_error)
                .crossFade()
                .into(photoView);

            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
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
