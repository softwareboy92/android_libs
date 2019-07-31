package com.lv.mvp.holder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.lv.libmvp.recycleview.AbsHolder;
import com.lv.libmvp.recycleview.AbsItemHolder;
import com.lv.mvp.R;
import com.lv.mvp.model.Car;

/**
 * 作者：created by albert on 2019-07-31 18:09
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class FoolerViewHolder extends AbsItemHolder<Car, FoolerViewHolder.ViewHolder> {

    public FoolerViewHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_foolter;
    }

    @Override
    public FoolerViewHolder.ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull FoolerViewHolder.ViewHolder holder, @NonNull Car item) {

    }

    public static class ViewHolder extends AbsHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }
}
