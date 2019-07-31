package com.lv.mvp.holder;


import android.content.Context;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.lv.libmvp.recycleview.AbsHolder;
import com.lv.libmvp.recycleview.AbsItemHolder;
import com.lv.mvp.R;
import com.lv.mvp.model.ListViewModel;

/**
 * 作者：created by albert on 2019-07-31 18:09
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class CostomViewHolder extends AbsItemHolder<ListViewModel, CostomViewHolder.ViewHolder> {


    public CostomViewHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_01;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ListViewModel item) {
        holder.mTextView1.setText(item.getTitle());
        holder.mTextView2.setText(item.getContract());
    }

    static class ViewHolder extends AbsHolder {

        private TextView mTextView1;
        private TextView mTextView2;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.tv_title);
            mTextView2 = itemView.findViewById(R.id.tv_content);
        }

    }
}
