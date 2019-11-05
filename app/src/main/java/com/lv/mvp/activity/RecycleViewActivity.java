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

import org.salient.artplayer.MediaPlayerManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：created by albert on 2019-07-31 14:19
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
@SuppressWarnings("all")
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
                .build();
        mRecyclerView.setAdapter(mAdapter);

        List<String> mImages = new ArrayList<>();
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564643049484&di=8f2fb87cdf09670084fea4dfd21cb8d1&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fcefc1e178a82b9019e14d38c798da9773812efd0.jpg");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564642990384&di=47e30615c7dc8e135de10bd411145a85&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F8ad4b31c8701a18b624702f0942f07082938fee5.jpg");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564642990384&di=009dd1ac656c13e9a9903792d1c37521&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fe1fe9925bc315c60c9fcca4987b1cb134954772f.jpg");
        items.add(new Persion("WangWU", 0, 18, "2019年07月26日16:40:36",mImages));


        //视频Video1 驯龙高手
        items.add(new ListViewModel("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572960755306&di=f1f3dd12c04aae89bbcca7613d33c5a2&imgtype=0&src=http%3A%2F%2Fwww.dzwww.com%2Fyule%2Fyulezhuanti%2Fmtcbg%2F201408%2FW020140814296178978164.jpg",
                "有人听前面猜出来了吗？",
                "在抖音，记录美好生活#"));
        //视频Video2 天地对决
        items.add(new ListViewModel("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2769403760,1422465498&fm=26&gp=0.jpg",
                "你真是个可爱又浪漫的麻烦",
                "在抖音，记录美好生活#"));
        //视频Video紧急救援
        items.add(new ListViewModel("http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4",
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2872500312,2890677947&fm=26&gp=0.jpg",
                "我不要做胆小鬼，我想看到你点的小心心",
                "在抖音，记录美好生活#"));
        //视频Video 玩具总动员
        items.add(new ListViewModel("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4",
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4197932224,1694423239&fm=26&gp=0.jpg",
                "女朋友演绎女孩子该如何正确的发出笑声",
                "女朋友演绎女孩子该如何正确的发出笑声"));
        //视频Video 叶问4
        items.add(new ListViewModel("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572960631985&di=c0ad335ec056586391714c0516572d74&imgtype=jpg&src=http%3A%2F%2Fphotocdn.sohu.com%2F20160304%2Fmp61908174_1457088690323_29.jpeg","中国的消费力已经在改变世界 ","中国的消费力已经在改变世界 "));



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

    @Override
    public void onBackPressed() {
        if (MediaPlayerManager.instance().backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaPlayerManager.instance().pause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerManager.instance().releasePlayerAndView(this);
    }


}
