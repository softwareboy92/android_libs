package com.lv.libmvp.adapter;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者：create by albert on 2018/10/11 1:18 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> viewArray = new SparseArray<>();

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes final int viewId) {
        View view = viewArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewArray.put(viewId, view);
        }
        return (T) view;
    }
}