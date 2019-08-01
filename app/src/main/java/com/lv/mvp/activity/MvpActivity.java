package com.lv.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lv.libimage.progress.CircleProgressView;
import com.lv.libimage.view.GlideImageView;
import com.lv.libmvp.activity.BaseActivity;
import com.lv.mvp.R;
import com.lv.mvp.adapter.RecycleViewAdapter;
import com.lv.mvp.contract.MvpContract;
import com.lv.mvp.presenter.MvpPresenter;

/**
 * 作者：created by albert on 2019-07-19 14:24
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class MvpActivity extends BaseActivity<MvpPresenter> implements MvpContract.View {

    public static final String KEY_IMAGE_URL = "image_url";
    public static final String KEY_IMAGE_URL_THUMBNAIL = "image_url_thumbnail";

    GlideImageView glideImageView;
    CircleProgressView progressView;

    String image_url;
    String image_url_thumbnail;


    @Override
    protected int getBarColor() {
        return R.color.white;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        image_url = getIntent().getStringExtra(KEY_IMAGE_URL);
        image_url_thumbnail = getIntent().getStringExtra(KEY_IMAGE_URL_THUMBNAIL);
        glideImageView = findViewById(R.id.glideImageView);
        progressView = findViewById(R.id.progressView);
        glideImageView.setOnClickListener(v -> onBackPressed());
        loadImage();
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

    private void loadImage() {
        glideImageView.load(image_url, android.R.color.transparent, (isComplete, percentage, bytesRead, totalBytes) -> {
            if (isComplete) {
                progressView.setVisibility(View.GONE);
            } else {
                progressView.setVisibility(View.VISIBLE);
                progressView.setProgress(percentage);
            }
        });
    }
}
