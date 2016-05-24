package com.luosifan.photopicker;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.luosifan.photopicker.adapter.PhotoPreviewAdapter;
import com.luosifan.photopicker.picker.PickerParams;
import com.luosifan.photopicker.view.FixedViewPager;

import java.util.ArrayList;

/**
 * 照片预览
 * Created by wzfu on 16/5/23.
 */
public class PhotoPreviewActivity extends AppCompatActivity implements
        OnPhotoClickListener, ViewPager.OnPageChangeListener {

    private FixedViewPager viewPager;
    private PickerParams pickerParams;
    private ImageLoader imageLoader;

    private ArrayList<String> paths = new ArrayList<>();
    private PhotoPreviewAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.NO_ACTIONBAR);
        setContentView(R.layout.photo_pager_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewPager = (FixedViewPager) findViewById(R.id.viewPager);

        pickerParams = (PickerParams) getIntent().getSerializableExtra(PhotoPicker.PARAMS);

        try {
            imageLoader = pickerParams.imageLoaderClass.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        if(pickerParams.selectedPaths != null){
            paths = pickerParams.selectedPaths;
        }

        adapter = new PhotoPreviewAdapter(this, paths, imageLoader);
        adapter.setPhotoViewClickListener(this);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(pickerParams.currentIndex);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(this);

        updateActionBarTitle();
    }

    public void updateActionBarTitle() {
        getSupportActionBar().setTitle(
                getString(R.string.action_button_string,
                        getString(R.string.action_done),
                        viewPager.getCurrentItem() + 1, paths.size()));
    }

    @Override
    public void OnPhotoTapListener() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        updateActionBarTitle();
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
