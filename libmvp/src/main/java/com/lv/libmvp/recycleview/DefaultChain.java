package com.lv.libmvp.recycleview;

import androidx.annotation.NonNull;

public class DefaultChain<T> implements Chain<T> {
    @Override
    public int indexItem(int var1, @NonNull T var2) {
        return 0;
    }
}