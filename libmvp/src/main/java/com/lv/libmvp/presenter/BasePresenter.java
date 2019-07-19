package com.lv.libmvp.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lv.libmvp.contract.BaseContract;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 作者：create by albert on 2018/11/11 5:18 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {


    protected T mView;

    protected CompositeDisposable disposables = new CompositeDisposable();


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
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