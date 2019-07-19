package com.lv.mvp.contract;

import com.lv.libmvp.contract.BaseContract;

/**
 * 作者：created by albert on 2019-07-19 14:24
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public interface MvpContract {

    interface View extends BaseContract.BaseView{}
    interface Presenter extends BaseContract.BasePresenter<View>{}
}
