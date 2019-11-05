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


        //视频Video1
        items.add(new ListViewModel("http://v9-dy.ixigua.com/743ffe9e501c5dac59ac90c1d06c67c1/5dc13e27/video/m/22093c415449c914054a64cb836f97fbf281163d3cd3000048db5f58267e/?a=1128&br=918&cr=0&cs=0&dr=0&ds=3&er=&l=20191105161707010155019091746364&lr=aweme&qs=0&rc=amY4dm1mc2Y8cDMzO2kzM0ApaDk4ZDY2M2RpN2RmZDU7O2dgb29uL2cubGlfLS02LS9zczQtNTRhMDBhYDFgNWJfYzI6Yw%3D%3D",
                "https://p9.pstatp.com/large/tos-cn-p-0015/b3822bb8323c403fbff054ceeddcdd6b_1571462928.jpg"));
        //视频Video2
        items.add(new ListViewModel("http://vfx.mtime.cn/Video/2018/06/27/mp4/180627094726195356.mp4",
                "https://p3.pstatp.com/large/tos-cn-p-0015/76ad7b717faa413bbab22998ba19d029.jpg"));
        //视频Video3
        items.add(new ListViewModel("https://aweme.snssdk.com/aweme/v1/playwm/?s_vid=93f1b41336a8b7a442dbf1c29c6bbc5693230f09b1f3912eba20ece509238434bb569cad1a0331250fa07a5d6652f9388faa748bc40ea49d0577c2645700050e&line=0",
                "https://p1.pstatp.com/large/tos-cn-p-0015/034e0f09a5524344aefa474a51ce4fc3_1572692772.jpg"));
        //视频Video4
        items.add(new ListViewModel("https://aweme.snssdk.com/aweme/v1/playwm/?s_vid=93f1b41336a8b7a442dbf1c29c6bbc562036dd60d437e06300f3c3176dfbed74ad6b27d32b3514e9806ca4128d8c3c3bba7569c767ace5a45bbca7cd8921fab9&line=0",
                "https://p1.pstatp.com/large/tos-cn-p-0015/67ed2530cbd645fab9d7c131f4c95128_1572829804.jpg"));
        //视频Video5
        items.add(new ListViewModel("https://aweme.snssdk.com/aweme/v1/playwm/?s_vid=93f1b41336a8b7a442dbf1c29c6bbc56bf95f6cca9500dcaa10ac0b2d827fee1bed35e59d473f117bc972a969b8fe4cc76207d997f179d364b3e238a586d6bef&line=0",
                "https://p1.pstatp.com/large/tos-cn-p-0015/b3d93f7dcdb147afb0c48e68f8c337b5.jpg"));

        //视频Video6
        items.add(new ListViewModel("https://aweme.snssdk.com/aweme/v1/playwm/?s_vid=93f1b41336a8b7a442dbf1c29c6bbc5676a3012c3fcf62f71cd282271feeaa47b957bba0d9437e1adadd890b908119d149787d413d9d8197d8bc2bcbeedad52d&line=0",
                "https://p1.pstatp.com/large/tos-cn-p-0015/8137868ff7ea4dd99b5c6158cbe61597.jpg"));


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
