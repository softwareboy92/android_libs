package com.lv.libmvp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.lv.libmvp.recycleview.OnItemClickListener;
import com.lv.libmvp.recycleview.OnItemLongClickListener;
import com.lv.libmvp.recycleview.Chain;
import com.lv.libmvp.recycleview.ChainOneToMany;
import com.lv.libmvp.recycleview.OneToMany;
import com.lv.libmvp.recycleview.VHolder;
import com.lv.libmvp.recycleview.ViewTypes;



import java.util.Collections;
import java.util.List;

import static com.lv.libmvp.recycleview.Preconditions.checkNotNull;

public class DelegateAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final String TAG = DelegateAdapter.class.getSimpleName();

    private LayoutInflater inflater;

    private List<?> datas;

    private ViewTypes viewTypes;

    private OnItemLongClickListener mOnItemLongClickListener;

    private OnItemClickListener mOnItemClickListener;

    private DelegateAdapter.Builder builder;

    public DelegateAdapter(Builder builder) {
        checkNotNull(builder);
        datas = Collections.emptyList();
        this.builder = builder;
        this.viewTypes = builder.viewTypes;
        this.mOnItemClickListener = builder.mOnItemClickListener;
        this.mOnItemLongClickListener = builder.mOnItemLongClickListener;
    }

    public void setDatas(List<?> datas) {
        checkNotNull(datas);
        this.datas = datas;
    }

    public List<?> getItems() {
        return datas;
    }


    public ViewTypes getTypes() {
        return viewTypes;
    }

    @Override
    public final int getItemCount() {
        return datas.size();
    }

    @Override
    public final int getItemViewType(int position) {
        int mViewType = getTypeOfIndex(position, datas.get(position));
        return mViewType;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null == inflater) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        VHolder<?, ?> vHolder = viewTypes.getItemView(viewType);
        return vHolder.onCreateViewHolder(inflater, parent);
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onBindViewHolder(holder, position, Collections.emptyList());
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, final int position, @NonNull List<Object> payloads) {
        VHolder vHolder = viewTypes.getItemView(holder.getItemViewType());
        vHolder.onBindViewHolder(holder, datas.get(position), payloads);
        if (null != mOnItemClickListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, position, datas.get(position));
                }
            });
        }
        if (null != mOnItemLongClickListener) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(v, position, datas.get(position));
                    return true;
                }
            });
        }
    }

    @Override
    public final long getItemId(int position) {
        int viewType = getItemViewType(position);
        VHolder itemView = viewTypes.getItemView(viewType);
        return itemView.getItemId(datas.get(position));
    }


    @Override
    public final void onViewRecycled(@NonNull ViewHolder holder) {
        onViewByHolder(holder).onViewRecycled(holder);
    }


    @Override
    public final boolean onFailedToRecycleView(@NonNull ViewHolder holder) {
        return onViewByHolder(holder).onFailedToRecycleView(holder);
    }


    @Override
    public final void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        onViewByHolder(holder).onViewAttachedToWindow(holder);
    }


    @Override
    public final void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        onViewByHolder(holder).onViewDetachedFromWindow(holder);
    }

    private VHolder onViewByHolder(@NonNull RecyclerView.ViewHolder holder) {
        return viewTypes.getItemView(holder.getItemViewType());
    }

    public int getTypeOfIndex(int position, @NonNull Object item) {
        //获取储存Item类型class集合的索引
        int index = this.viewTypes.getClassIndexOf(item.getClass());
        if (index != -1) {
            Chain<Object> chain = (Chain<Object>) this.viewTypes.getChain(index);
            Log.e(TAG, index + "---" + chain.indexItem(position, item));
            return index + chain.indexItem(position, item);
        }
        return -1;
    }

    public static class Builder<T> {
        /**
         * item类型容器
         */
        private ViewTypes viewTypes;

        private Class<? extends T> clazz;

        private VHolder<T, ?>[] vHolders;

        private OnItemLongClickListener mOnItemLongClickListener;

        private OnItemClickListener mOnItemClickListener;

        public Builder() {
            viewTypes = new ViewTypes();
        }

        /**
         * 数据类型一对一
         *
         * @param clazz
         * @param vHolder
         * @return
         */
        public Builder bind(Class<? extends T> clazz, VHolder vHolder) {
            checkNotNull(clazz);
            checkNotNull(vHolder);
            this.viewTypes.save(clazz, vHolder);
            return this;
        }

        /**
         * 数据类型一对多
         *
         * @param clazz
         * @param vHolders
         * @return
         */
        public Builder bindArray(Class<? extends T> clazz, VHolder... vHolders) {
            checkNotNull(clazz);
            checkNotNull(vHolders);
            this.clazz = clazz;
            this.vHolders = vHolders;
            return this;
        }

        public Builder withClass(OneToMany<T> oneToMany) {
            checkNotNull(oneToMany);
            Chain<T> chain = new ChainOneToMany(oneToMany, vHolders);
            for (VHolder itemView : vHolders) {
                viewTypes.save(this.clazz, itemView, chain);
            }
            return this;
        }

        public Builder setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
            return this;
        }

        public Builder setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
            this.mOnItemLongClickListener = onItemLongClickListener;
            return this;
        }

        public DelegateAdapter build() {
            return new DelegateAdapter(this);
        }

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }
}
