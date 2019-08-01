package com.lv.mvp.adapter;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;

public class MyGridLayoutManager extends GridLayoutManager {

    private boolean isScrollEnabled = true;
 
    public MyGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
 
    public MyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }
 
    public MyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }
 
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }
 
    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}