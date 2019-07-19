package com.lv.libhttp.factory;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by albert
 * on 2018/1/30.
 * Email:lvzhongdi@icloud.com
 */

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }
    public abstract void onSuccess(T t);
    public abstract void onFail(Throwable e);
}