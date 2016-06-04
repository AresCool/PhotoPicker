package com.luosifan.photopicker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 修复图片在ViewPager控件中缩放报错的BUG
 *
 */
public class FixedViewPager extends ViewPager {

    public FixedViewPager(Context context) {
        super(context);
    }

    public FixedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
        }
        return false;
    }

//
//    private boolean mIsDisallowIntercept = false;
//
//    @Override
//    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//        mIsDisallowIntercept = disallowIntercept;
//        super.requestDisallowInterceptTouchEvent(disallowIntercept);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
//        if (ev.getPointerCount() > 1 && mIsDisallowIntercept) {
//            requestDisallowInterceptTouchEvent(false);
//            boolean handled = super.dispatchTouchEvent(ev);
//            requestDisallowInterceptTouchEvent(true);
//            return handled;
//        } else {
//            return super.dispatchTouchEvent(ev);
//        }
//    }
}
