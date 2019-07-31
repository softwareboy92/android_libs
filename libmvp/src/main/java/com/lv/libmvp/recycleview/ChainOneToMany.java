package com.lv.libmvp.recycleview;


public final class ChainOneToMany<T> implements Chain<T> {

    private final OneToMany<T> oneToMany;

    private final VHolder<T, ?>[] vHolders;


    public ChainOneToMany(
            OneToMany<T> oneToMany,
            VHolder<T, ?>[] vHolders) {
        this.oneToMany = oneToMany;
        this.vHolders = vHolders;
    }

    @Override
    public int indexItem(int position, T t) {
        Class<?> aClass = oneToMany.onItemView(position, t);
        for (int i = 0; i < vHolders.length; i++) {
            if (vHolders[i].getClass().equals(aClass)) {
                return i;
            }
        }
        return -1;

    }
}