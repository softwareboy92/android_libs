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
    protected int getBarColor() {
        return R.color.white;
    }

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

        List<String> mImages = new ArrayList<>();
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564643049484&di=8f2fb87cdf09670084fea4dfd21cb8d1&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fcefc1e178a82b9019e14d38c798da9773812efd0.jpg");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564642990384&di=47e30615c7dc8e135de10bd411145a85&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F8ad4b31c8701a18b624702f0942f07082938fee5.jpg");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564642990384&di=009dd1ac656c13e9a9903792d1c37521&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fe1fe9925bc315c60c9fcca4987b1cb134954772f.jpg");
        items.add(new Persion("WangWU", 0, 18, "2019年07月26日16:40:36",mImages));

        for (int i = 0; i < 5; i++) {
            items.add(new ListViewModel("Title" + i, "这里是内容" + i));
        }

        items.add(new Car("奥迪", "110100", "黑色"));

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
