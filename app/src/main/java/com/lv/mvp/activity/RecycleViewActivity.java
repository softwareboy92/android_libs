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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.lv.libmvp.activity.BaseActivity;
import com.lv.libmvp.adapter.DelegateAdapter;
import com.lv.libmvp.model.ItemData;
import com.lv.libmvp.recycleview.TypeVo;
import com.lv.mvp.R;
import com.lv.mvp.contract.RecycleViewContract;
import com.lv.mvp.holder.CostomViewHolder;
import com.lv.mvp.holder.FoolerViewHolder;
import com.lv.mvp.holder.HeadlerViewHolder;
import com.lv.mvp.model.Car;
import com.lv.mvp.model.ListViewModel;
import com.lv.mvp.model.Persion;
import com.lv.mvp.presenter.RecycleViewPresenter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：created by albert on 2019-07-31 14:19
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class RecycleViewActivity extends BaseActivity<RecycleViewPresenter> implements RecycleViewContract.View {


    //    private RecycleViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    //    private LayoutInflater mInflater;
    private DelegateAdapter mAdapter;
    private WeakReference<RecycleViewActivity> weakReference;
    protected ItemData items = new ItemData();


    @Override
    protected void initView(Bundle savedInstanceState) {
//        mInflater = LayoutInflater.from(this);
        weakReference = new WeakReference<>(this);
        mRecyclerView = findViewById(R.id.recycleview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
//        View emptyView = mInflater.inflate(R.layout.item_empty_view, (ViewGroup) mRecyclerView.getParent(), false);
//        mAdapter = new RecycleViewAdapter();
//        mAdapter.setEmptyView(emptyView);
//        View headerView = mInflater.inflate(R.layout.item_header, (ViewGroup) mRecyclerView.getParent(), false);
//        mAdapter.setHeaderView(headerView);
//        View footerView = mInflater.inflate(R.layout.item_foolter, (ViewGroup) mRecyclerView.getParent(), false);
//        mAdapter.setFooterView(footerView);
//        mRecyclerView.setAdapter(mAdapter);
//        List<ListViewModel> mList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            mList.add(new ListViewModel());
//        }
//        mAdapter.setData(mList);
//        mAdapter.notifyDataSetChanged();

        mAdapter = new DelegateAdapter.Builder<>()
                .bind(Persion.class, new HeadlerViewHolder(weakReference.get()))
                .bind(ListViewModel.class, new CostomViewHolder(weakReference.get()))
                .bind(Car.class, new FoolerViewHolder(weakReference.get()))
//                .bind(TypeVo.class,new TypeItemView(weakReference.get()))
                .build();
        mRecyclerView.setAdapter(mAdapter);
        for (int i = 0; i < 5; i++) {
            items.add(new ListViewModel("Title" + i, "这里是内容" + i));
        }
        for (int i = 0; i < 10; i++) {
            items.add(new Persion("张三", 0, 18, "2019年07月26日16:40:36"));
        }
        for (int i = 0; i < 5; i++) {
            items.add(new Car("奥迪", "110100", "黑色"));
        }
        mAdapter.setDatas(items);
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
