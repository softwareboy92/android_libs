package com.lv.libmvp.recycleview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.lv.libmvp.adapter.DelegateAdapter;

import java.util.List;

public abstract class VHolder<T, VH extends ViewHolder> {

    DelegateAdapter adapter;

    public abstract @NonNull VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);


    protected abstract void onBindViewHolder(@NonNull VH holder, @NonNull T item);


    public void onBindViewHolder(@NonNull VH holder, @NonNull T item, @NonNull List<Object> payloads) {
        holder.itemView.setTag(item);
        onBindViewHolder(holder, item);
    }


    protected final int getPosition(@NonNull final ViewHolder holder) {
        return holder.getAdapterPosition();
    }


    protected final @NonNull
    DelegateAdapter getAdapter() {
        if (adapter == null) {
            throw new IllegalStateException("VHolder " + this + " not attached to DelegateAdapter. ");
        }
        return adapter;
    }


    public long getItemId(@NonNull T item) {
        return item.hashCode();
    }


    public void onViewRecycled(@NonNull VH holder) {
    }


    public boolean onFailedToRecycleView(@NonNull VH holder) {
        return false;
    }


    public void onViewAttachedToWindow(@NonNull VH holder) {
    }


    public void onViewDetachedFromWindow(@NonNull VH holder) {
    }
}
