package com.lv.mvp.adapter;

import android.content.Context;

import com.lv.libmvp.adapter.BaseMultiTypeRecycleAdapter;
import com.lv.libmvp.adapter.CommonViewHolder;
import com.lv.libmvp.listener.MultiTypeSupport;
import com.lv.mvp.R;
import com.lv.mvp.model.HotCoinsResponse;

import java.util.List;

/**
 * 作者：created by albert on 2019-07-18 15:46
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class MultiTypeAdapter extends BaseMultiTypeRecycleAdapter<HotCoinsResponse> implements MultiTypeSupport<HotCoinsResponse> {


    private CommonViewHolder.onItemCommonClickListener commonClickListener;

    public MultiTypeAdapter(Context context, List<HotCoinsResponse> dataList) {
        super(context, dataList, R.layout.item_01);
        this.multiTypeSupport = this;
    }
    public MultiTypeAdapter(Context context, List<HotCoinsResponse> dataList, CommonViewHolder.onItemCommonClickListener commonClickListener) {
        super(context, dataList, R.layout.item_01);
        this.commonClickListener = commonClickListener;
        this.multiTypeSupport = this;
    }


    @Override
    public int getLayoutId(HotCoinsResponse item, int position) {
        if (item.getSymbol().equals("HT")) {
            return R.layout.item_01;
        }
        return R.layout.item_02;
    }

    @Override
    protected void bindData(CommonViewHolder holder, HotCoinsResponse data) {
        holder.setText(R.id.tv_title, data.getCategory())
                .setText(R.id.tv_content,data.getTokenAddress())
                .setCommonClickListener(commonClickListener);
    }
}
