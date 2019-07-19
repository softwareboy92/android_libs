package com.lv.mvp.presenter;

import android.app.Activity;
import com.lv.libhttp.factory.BaseObserver;
import com.lv.libmvp.presenter.BasePresenter;
import com.lv.mvp.contract.MainContract;
import com.lv.mvp.http.Loader;
import com.lv.mvp.model.HotCoinsResponse;
import com.lv.mvp.model.UpdateResponse;

/**
 * 作者：created by albert on 2019年07月18日16:54:14
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    @Override
    public void getVersion(Activity activity) {
        if (mView != null && checkNet(activity)) {
            mView.showNoNet();
            return;
        }
        mView.showLoading();
        Loader.getLoader().getAppVersion()
                .compose(mView.bindToLife())
                .subscribe(new BaseObserver<UpdateResponse>() {
                    @Override
                    public void onSuccess(UpdateResponse response) {
                        mView.hideLoading();
                        if (response.isSuccess()) {

                        }
                    }
                    @Override
                    public void onFail(Throwable e) {
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void getHotCoins(Activity activity) {
        if (mView != null && checkNet(activity)) {
            mView.showNoNet();
            return;
        }
        mView.showLoading();
        Loader.getLoader().getHotCoins().compose(mView.bindToLife())
                .subscribe(new BaseObserver<HotCoinsResponse>() {
                    @Override
                    public void onSuccess(HotCoinsResponse response) {
                        mView.hideLoading();
                        if (response.isSuccess()) {
                            mView.onSuccess(response.data);
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        mView.hideLoading();
                    }
                });

    }
}
