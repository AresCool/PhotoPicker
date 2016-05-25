package com.luosifan.photopicker.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.luosifan.photopicker.ImageLoader;
import com.luosifan.photopicker.R;
import com.luosifan.photopicker.bean.Image;
import com.luosifan.photopicker.event.OnPhotoGridClickListener;
import com.luosifan.photopicker.view.GFImageView;

import java.util.List;

/**
 * Created by wzfu on 2016/5/25.
 */
public class PhotoGridAdapter extends SelectableAdapter {

    public final static int ITEM_TYPE_CAMERA = 100;
    public final static int ITEM_TYPE_PHOTO  = 101;

    private Context mCxt;
    private ImageLoader imageLoader;
    private LayoutInflater inflater;

    private int imageSize;
    private boolean showCamera = true;
    // 多选时显示指示器
    private boolean showSelectIndicator = true;
    private OnPhotoGridClickListener onPhotoGridClickListener;

    public PhotoGridAdapter(Context mCxt, boolean showCamera, int colNum, ImageLoader imageLoader){
        this.mCxt = mCxt;
        this.showCamera = showCamera;
        this.imageLoader = imageLoader;
        inflater = LayoutInflater.from(mCxt);

        WindowManager wm = (WindowManager) mCxt.getSystemService(Context.WINDOW_SERVICE);
        int width;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            width = size.x;
        } else {
            width = wm.getDefaultDisplay().getWidth();
        }
        imageSize = width / colNum;
    }

    public void setOnPhotoGridClickListener(OnPhotoGridClickListener listener) {
        this.onPhotoGridClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View itemView;
        if(viewType == ITEM_TYPE_PHOTO) {
            itemView = inflater.inflate(R.layout.list_item_image, parent, false);
            holder = new PhotoViewHolder(itemView);
        }else{
            itemView = inflater.inflate(R.layout.list_item_camera, parent, false);
            holder = new CaremaViewHolder(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onPhotoGridClickListener != null) {
                        onPhotoGridClickListener.onCameraClick();
                    }
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {

        if(getItemViewType(position) == ITEM_TYPE_PHOTO) {

            final PhotoViewHolder holder = (PhotoViewHolder) viewHolder;

            final Image image = mImages.get(showCamera() ? position - 1 : position);
            // 是否显示指示器
            holder.indicator.setVisibility(showSelectIndicator ? View.VISIBLE : View.GONE);
            // 设置选中状态和背景
            boolean selected = isSelected(image);
            holder.mask.setVisibility(selected ? View.VISIBLE : View.GONE);
            if(showSelectIndicator){
                holder.indicator.setImageResource(selected ? R.drawable.btn_selected
                        : R.drawable.btn_unselected);
            }
            // 显示图片
            if(imageLoader != null) {
                imageLoader.displayImage(holder.image.getContext(),
                        image.path,
                        holder.image,
                        R.id.photopicker_item_tag_id,
                        R.drawable.default_error,
                        R.drawable.default_error,
                        imageSize, imageSize);
            }

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = holder.getAdapterPosition();

                    if(onPhotoGridClickListener != null){
                        onPhotoGridClickListener.onPhotoClick(image, pos);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (showCamera() && position == 0) ? ITEM_TYPE_CAMERA : ITEM_TYPE_PHOTO;
    }

    @Override
    public int getItemCount() {
        int count = mImages.size();
        if(showCamera()){
            return count + 1;
        }
        return count;
    }

    public void setShowCamera(boolean b) {
        if (showCamera == b) return;
        this.showCamera = b;
        notifyDataSetChanged();
    }

    public boolean showCamera() {
        return showCamera;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder{

        GFImageView image;
        View mask;
        ImageView indicator;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            image = (GFImageView) itemView.findViewById(R.id.image);
            mask = itemView.findViewById(R.id.mask);
            indicator = (ImageView) itemView.findViewById(R.id.checkmark);
        }
    }

    public static class CaremaViewHolder extends RecyclerView.ViewHolder{

        public CaremaViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 显示选择指示器
     *
     * @param b
     */
    public void showSelectIndicator(boolean b) {
        showSelectIndicator = b;
    }


    /**
     * 选择某个图片，改变选择状态
     *
     * @param image
     */
    public void select(Image image, int pos) {
        toggleSelection(image);
        notifyItemChanged(pos);
    }
}
