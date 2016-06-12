package cc.dagger.photopicker.loader;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import cc.dagger.photopicker.R;


public abstract class ImageLoader<GV extends ImageView, PV extends ImageView> {

    protected int getPlaceholder() {
        return R.mipmap.default_image;
    }

    public abstract GV onCreateGridItemView(Context mCxt);

    public abstract void displayImage(Activity activity, String path, GV imageView, int width, int height);

    public abstract PV onCreatePreviewItemView(Context mCxt);

    public abstract void clearMemoryCache();
}