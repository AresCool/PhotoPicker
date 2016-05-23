package com.luosifan.photopicker;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.luosifan.photopicker.view.FixedViewPager;

/**
 * 照片预览
 * Created by wzfu on 16/5/23.
 */
public class PhotoPagerActivity extends AppCompatActivity {

    private FixedViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

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
    }
}
