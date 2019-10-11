package com.lv.libmvp.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lv.libmvp.contract.BaseContract;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 作者：create by albert on 2018/11/11 5:18 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {


    protected T mView;

    private WeakReference<T> mTWeakReference;


    protected CompositeDisposable disposables = new CompositeDisposable();


    @Override
    public void attachView(T view) {
//        this.mView = view;
        this.mTWeakReference = new WeakReference<T>(view);

        //用代理对象，动态代理
        mView = (T) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (mTWeakReference == null && mTWeakReference.get()==null){
                    return null;
                }else {
                    return method.invoke(mTWeakReference.get(),objects);
                }

            }
        });
    }

    @Override
    public void detachView() {
//        if (mView != null) {
//            mView = null;
//        }
        this.mTWeakReference.clear();
        this.mTWeakReference = null;
        this.mView = null;

    }

    public T getView() {
        return mView;
    }

    @Override
    public void cancelAllRequest() {
        disposables.clear();
    }


    protected boolean checkNet(Context context) {
        return isNetworkConnected(context);
    }


    @SuppressLint("MissingPermission")
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return !mNetworkInfo.isAvailable();
            }
        }
        return true;
    }

}