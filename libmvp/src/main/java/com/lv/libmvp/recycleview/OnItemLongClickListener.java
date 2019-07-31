package com.lv.libmvp.recycleview;

import android.view.View;

public interface OnItemLongClickListener<T> {
    void onItemLongClick(View view, int position, T t);
}