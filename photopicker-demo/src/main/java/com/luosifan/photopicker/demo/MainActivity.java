package com.luosifan.photopicker.demo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luosifan.photopicker.ImageLoader;
import com.luosifan.photopicker.PhotoPicker;
import com.luosifan.photopicker.demo.loader.FrescoImageLoader;
import com.luosifan.photopicker.demo.loader.GlideImageLoader;
import com.luosifan.photopicker.demo.loader.PicassoImageLoader;
import com.luosifan.photopicker.demo.loader.UILImageLoader;
import com.luosifan.photopicker.demo.loader.XUtilsImageLoader;
import com.luosifan.photopicker.picker.Load;
import com.luosifan.photopicker.picker.PhotoFilter;
import com.luosifan.photopicker.picker.PhotoSelectBuilder;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    private TextView mResultText;
    private RadioGroup mChoiceMode, mShowCamera;
    private EditText mRequestNum;
    private EditText mRequestColumns;
    private LinearLayout pick_size_layout;
    private Spinner spinner_imageloader;

    public static final String[] IMAGELODERS = {
            "Picasso",
            "Glide",
            "Fresco",
            "Universal-Image-Loader",
            "Xutils3"
    };

    private ArrayList<String> mSelectPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultText = (TextView) findViewById(R.id.result);
        mChoiceMode = (RadioGroup) findViewById(R.id.choice_mode);
        mShowCamera = (RadioGroup) findViewById(R.id.show_camera);
        mRequestNum = (EditText) findViewById(R.id.request_num);
        mRequestColumns = (EditText) findViewById(R.id.edit_colums);
        pick_size_layout = (LinearLayout) findViewById(R.id.pick_size_layout);
        spinner_imageloader = (Spinner) findViewById(R.id.spinner_imageloader);

        mChoiceMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId != R.id.multi) {
                    mRequestNum.setText("");
                }
                pick_size_layout.setVisibility(checkedId == R.id.multi ? View.VISIBLE : View.GONE);
            }
        });

        ArrayAdapter<String> loadersAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, IMAGELODERS);
        loadersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_imageloader.setAdapter(loadersAdapter);

        View button = findViewById(R.id.button);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pickImage();
                }
            });
        }
    }

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            boolean showCamera = mShowCamera.getCheckedRadioButtonId() == R.id.show;
            int maxNum = 9;

            if (!TextUtils.isEmpty(mRequestNum.getText())) {
                try {
                    maxNum = Integer.valueOf(mRequestNum.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            int columns = 3;
            if (!TextUtils.isEmpty(mRequestColumns.getText().toString())){
                columns = Integer.parseInt(mRequestColumns.getText().toString());
            }

            Load load = PhotoPicker.with(getImageLoader(spinner_imageloader.getSelectedItem().toString()))
                    .load()
                    .showCamera(showCamera)
//                    .filter(PhotoFilter.build().showGif(false).minSize(2 * 1024))
                    .gridColumns(columns);

            PhotoSelectBuilder builder;

            if (mChoiceMode.getCheckedRadioButtonId() == R.id.single) {
                builder = load.single();
            } else {
                builder = load.multi().maxPickSize(maxNum).selectedPaths(mSelectPath);
            }
            builder.start(MainActivity.this);
        }
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PhotoPicker.REQUEST_SELECTED) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(PhotoPicker.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for (String p : mSelectPath) {
                    sb.append(p);
                    sb.append("\n");
                }
                mResultText.setText(sb.toString());
            }
        }
    }

    private Class<? extends ImageLoader> getImageLoader(String selectedItem){
        Class<? extends ImageLoader> imageloader = null;
        switch (selectedItem){
            case "Picasso":
                imageloader = PicassoImageLoader.class;
                break;
            case "Glide":
                imageloader = GlideImageLoader.class;
                break;
            case "Fresco":
                imageloader = FrescoImageLoader.class;
                break;
            case "Universal-Image-Loader":
                imageloader = UILImageLoader.class;
                break;
            case "Xutils3":
                imageloader = XUtilsImageLoader.class;
                break;
        }
        return imageloader;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
