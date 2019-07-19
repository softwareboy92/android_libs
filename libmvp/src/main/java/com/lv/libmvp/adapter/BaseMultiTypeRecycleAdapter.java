package com.lv.libmvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.lv.libmvp.listener.MultiTypeSupport;

import java.util.List;

/**
 * 作者：created by albert on 2019-07-18 15:22
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public abstract class BaseMultiTypeRecycleAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    protected LayoutInflater layoutInflater;
    protected List<T> dataList;
    protected int layoutId;
    protected MultiTypeSupport<T> multiTypeSupport;
    private Context mContext;


    public BaseMultiTypeRecycleAdapter(Context context, List<T> dataList, int layoutId) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.dataList = dataList;
        this.layoutId = layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        if (multiTypeSupport != null) {
            return multiTypeSupport.getLayoutId(dataList.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (multiTypeSupport != null) {
            layoutId = viewType;
        }
        View itemView = layoutInflater.inflate(layoutId, parent, false);
        return new CommonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        bindData(holder, dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected abstract void bindData(CommonViewHolder holder, T data);

}
