package com.lv.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lv.libmvp.activity.BaseActivity;
import com.lv.mvp.R;
import com.lv.mvp.contract.MvpContract;
import com.lv.mvp.presenter.MvpPresenter;

/**
 * 作者：created by albert on 2019-07-19 14:24
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class MvpActivity extends BaseActivity<MvpPresenter> implements MvpContract.View {

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected MvpPresenter initPresenter() {
        return new MvpPresenter();
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void refershDatas(Context context, Intent intent) {

    }
}
