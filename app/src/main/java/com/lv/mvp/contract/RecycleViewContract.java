package com.lv.mvp.contract;

import com.lv.libmvp.contract.BaseContract;

/**
 * 作者：created by albert on 2019-07-31 14:19
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public interface RecycleViewContract {

    interface View extends BaseContract.BaseView{}


    interface Presenter extends BaseContract.BasePresenter<View>{}
}
