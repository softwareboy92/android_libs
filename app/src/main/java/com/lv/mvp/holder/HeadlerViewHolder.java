package com.lv.mvp.holder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.lv.libmvp.recycleview.AbsHolder;
import com.lv.libmvp.recycleview.AbsItemHolder;
import com.lv.mvp.R;
import com.lv.mvp.model.Persion;

/**
 * 作者：created by albert on 2019-07-31 18:08
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class HeadlerViewHolder extends AbsItemHolder<Persion, HeadlerViewHolder.ViewHolder> {



    public HeadlerViewHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_header;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Persion item) {

    }

    public static class ViewHolder extends AbsHolder {


        public ViewHolder(View view) {
            super(view);
        }
    }
}
