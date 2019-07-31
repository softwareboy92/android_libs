package com.lv.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lv.libmvp.activity.BaseActivity;
import com.lv.mvp.R;
import com.lv.mvp.adapter.RecycleViewAdapter;
import com.lv.mvp.contract.RecycleViewContract;
import com.lv.mvp.model.ListViewModel;
import com.lv.mvp.presenter.RecycleViewPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：created by albert on 2019-07-31 14:19
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class RecycleViewActivity extends BaseActivity<RecycleViewPresenter> implements RecycleViewContract.View {


    private RecycleViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LayoutInflater mInflater;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(this);
        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        View emptyView = mInflater.inflate(R.layout.item_empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter = new RecycleViewAdapter();
        mAdapter.setEmptyView(emptyView);
        View headerView = mInflater.inflate(R.layout.item_header, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter.setHeaderView(headerView);
        View footerView = mInflater.inflate(R.layout.item_foolter, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter.setFooterView(footerView);
        mRecyclerView.setAdapter(mAdapter);
        List<ListViewModel> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(new ListViewModel());
        }
        mAdapter.setData(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected RecycleViewPresenter initPresenter() {
        return new RecycleViewPresenter();
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_recycleview;
    }

    @Override
    protected void refershDatas(Context context, Intent intent) {

    }
}
