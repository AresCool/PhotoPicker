package com.luosifan.photopicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wzfu on 2016/5/11.
 */
public class PhotoPickerActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_picker_layout);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, PhotoPickerFragment.newInstance())
                .commit();
        getSupportFragmentManager().executePendingTransactions();
    }
}
