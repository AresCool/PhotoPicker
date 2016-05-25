package com.luosifan.photopicker.event;

import android.support.v7.widget.RecyclerView;

import com.luosifan.photopicker.ImageLoader;
import com.luosifan.photopicker.R;

/**
 * Updated by wzfu 2016/05/25
 */
public class PauseOnScrollListener extends RecyclerView.OnScrollListener{

    private final boolean pauseOnScroll;
    private final boolean pauseOnFling;
    private final ImageLoader imageLoader;

    private static final int TAG_ID = R.id.photopicker_item_tag_id;

    public PauseOnScrollListener(boolean pauseOnScroll, boolean pauseOnFling, ImageLoader imageLoader) {
        this.pauseOnScroll = pauseOnScroll;
        this.pauseOnFling = pauseOnFling;
        this.imageLoader = imageLoader;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if(imageLoader != null) {
            switch (newState) {

                case RecyclerView.SCROLL_STATE_IDLE:
                    resume(recyclerView);
                    break;

                case RecyclerView.SCROLL_STATE_DRAGGING:
                    if (pauseOnScroll) {
                        pause(recyclerView);
                    } else {
                        resume(recyclerView);
                    }
                    break;

                case RecyclerView.SCROLL_STATE_SETTLING:
                    if (pauseOnFling) {
                        pause(recyclerView);
                    } else {
                        resume(recyclerView);
                    }
                    break;
            }
        }
    }

    private void pause(RecyclerView recyclerView){
        imageLoader.pauseRequests(recyclerView.getContext(), TAG_ID);
    }

    private void resume(RecyclerView recyclerView){
        imageLoader.resumeRequests(recyclerView.getContext(), TAG_ID);
    }
}
