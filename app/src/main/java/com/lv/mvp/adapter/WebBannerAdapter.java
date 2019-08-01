package com.lv.mvp.adapter;


import com.lv.libimage.view.GlideImageView;
import com.lv.libmvp.adapter.BaseSimpleRecycleViewAdapter;
import com.lv.libmvp.adapter.BaseViewHolder;
import com.lv.mvp.R;

/**
 * 作者：created by albert on 2019-08-01 11:32
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class WebBannerAdapter extends BaseSimpleRecycleViewAdapter<String> {
    @Override
    protected int bindLayout(int viewType) {
        return R.layout.item_image;
    }

    @Override
    protected void bind(BaseViewHolder holder, String data) {
        GlideImageView imageView = holder.itemView.findViewById(R.id.image);
        imageView.enableState(true).load(data);
    }
}
