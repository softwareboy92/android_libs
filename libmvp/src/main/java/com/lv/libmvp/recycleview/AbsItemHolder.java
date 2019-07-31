package com.lv.libmvp.recycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public abstract class AbsItemHolder<T, VH extends AbsHolder> extends VHolder<T, VH> {

    protected Context mContext;

    public AbsItemHolder(Context context) {
        this.mContext = context;
    }

    @Override
    @NonNull
    public VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return createViewHolder(inflater.inflate(getLayoutResId(), parent, false));
    }

    public abstract int getLayoutResId();


    public abstract VH createViewHolder(View view);


}