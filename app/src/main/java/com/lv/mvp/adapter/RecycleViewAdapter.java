package com.lv.mvp.adapter;

import android.widget.TextView;

import com.lv.libmvp.adapter.BaseSimpleRecycleViewAdapter;
import com.lv.libmvp.adapter.BaseViewHolder;
import com.lv.mvp.R;
import com.lv.mvp.model.ListViewModel;

/**
 * 作者：created by albert on 2019-07-19 14:43
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class RecycleViewAdapter extends BaseSimpleRecycleViewAdapter<ListViewModel> {
    @Override
    protected int bindLayout(int viewType) {
        return R.layout.item_01;
    }

    @Override
    protected void bind(BaseViewHolder holder, ListViewModel data) {
        TextView tv_title = holder.itemView.findViewById(R.id.tv_title);
        TextView tv_content = holder.itemView.findViewById(R.id.tv_content);

        tv_title.setText(data.getTitle());
        tv_content.setText(data.getContract());
    }
}
