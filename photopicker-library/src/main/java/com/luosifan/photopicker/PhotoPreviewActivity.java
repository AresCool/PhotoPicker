package com.luosifan.photopicker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.luosifan.photopicker.picker.PagerFragment;
import com.luosifan.photopicker.picker.PickerTheme;
import com.luosifan.photopicker.picker.PreviewParams;

/**
 * 照片预览
 * Created by wzfu on 16/5/23.
 */
public class PhotoPreviewActivity extends AppCompatActivity {

    private PreviewParams params;
    private PagerFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NO_ACTIONBAR);
        setContentView(R.layout.activity_default);

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

        params = (PreviewParams) getIntent().getSerializableExtra(PhotoPicker.PARAMS_PREVIEW);

        try {
            fragment = params.previewFragmentClass.newInstance();

            Bundle bundle = new Bundle();
            bundle.putStringArrayList(PagerFragment.PHOTO_PATHS, params.paths);
            bundle.putInt(PagerFragment.CURRENT_ITEM, params.currentItem);
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
            getSupportFragmentManager().executePendingTransactions();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(params.theme != null) {
            changeTheme(params.theme);
        }
    }

    private void changeTheme(PickerTheme theme) {

    }

    private void back(){
        int resultCode = RESULT_CANCELED;
        if(fragment != null){
            resultCode = fragment.getResultCode();
        }
        setResult(resultCode);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                back();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // 返回键处理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}