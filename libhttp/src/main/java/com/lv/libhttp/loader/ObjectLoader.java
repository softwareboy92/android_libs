package com.lv.libhttp.loader;

import com.lv.libhttp.utils.AndroidScheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：created by albert on 2019-07-18 13:34
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class ObjectLoader {
    /**
     * @param observable
     * @param <T>
     * @return
     */
    protected <T> Observable<T> observe(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidScheduler.mainThread());
    }
}
