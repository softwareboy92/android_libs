package com.lv.mvp.holder;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.lv.libmvp.recycleview.AbsHolder;
import com.lv.libmvp.recycleview.AbsItemHolder;
import com.lv.mvp.R;
import com.lv.mvp.model.ListViewModel;

import org.salient.artplayer.Comparator;
import org.salient.artplayer.MediaPlayerManager;
import org.salient.artplayer.VideoView;
import org.salient.artplayer.exo.ExoPlayer;
import org.salient.artplayer.ui.ControlPanel;

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
        return R.layout.item_video;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ListViewModel item) {
        holder.mVideoView.setUp(item.getTitle(),VideoView.WindowType.LIST);
        holder.mVideoView.setControlPanel(new ControlPanel(mContext));
        MediaPlayerManager.instance().setMediaPlayer(new ExoPlayer(mContext));

        //setCover
        ImageView coverView = ((ControlPanel) holder.mVideoView.getControlPanel()).findViewById(R.id.video_cover);
        Glide.with(holder.mVideoView.getContext()).load(item.getContract()).into(coverView);


    }


    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }


    static class ViewHolder extends AbsHolder {

        private VideoView mVideoView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mVideoView = itemView.findViewById(R.id.item_video_view);
        }
    }
}
