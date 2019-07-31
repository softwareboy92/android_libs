package com.lv.libmvp.recycleview;

public interface OneToMany<T> {
    /**
     * 链接一对多
     * @param position
     * @param t
     * @return
     */
    Class<? extends VHolder<T, ?>> onItemView(int position, T t);
}