package com.lv.mvp.contract;

import android.app.Activity;

import com.lv.libmvp.contract.BaseContract;
import com.lv.mvp.model.HotCoinsResponse;

import java.util.List;

/**
 * 作者：created by albert on 2019-07-18 13:23
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public interface MainContract {

    interface View extends BaseContract.BaseView{
        void onSuccess(List<HotCoinsResponse> mList);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{

        void getVersion(Activity activity);

        void getHotCoins(Activity activity);
    }
}
