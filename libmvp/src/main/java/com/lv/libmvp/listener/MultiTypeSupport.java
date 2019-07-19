package com.lv.libmvp.listener;

public interface MultiTypeSupport<T> {
    int getLayoutId(T item, int position);
}