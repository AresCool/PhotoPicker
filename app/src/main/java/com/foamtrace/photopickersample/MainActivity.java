package com.foamtrace.photopickersample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView tvUrls;

    private ArrayList<String> imagePaths = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUrls = (TextView) findViewById(R.id.tvUrls);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent = new PhotoPickerIntent(MainActivity.this);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true);
                intent.setMaxTotal(6);
                intent.setSelectedPaths(imagePaths);
                startActivityForResult(intent, PhotoPickerActivity.REQUEST_PHOTO_PICKER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case PhotoPickerActivity.REQUEST_PHOTO_PICKER:
                    imagePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    if (imagePaths != null) {
                        tvUrls.setText(imagePaths.toString());
                    }
                    break;
            }
        }
    }
}
