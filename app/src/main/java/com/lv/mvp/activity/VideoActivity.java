package com.lv.mvp.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.lv.mvp.R;

import org.salient.artplayer.MediaPlayerManager;
import org.salient.artplayer.VideoView;
import org.salient.artplayer.exo.ExoPlayer;
import org.salient.artplayer.ui.ControlPanel;

import me.jessyan.autosize.utils.LogUtils;

/**
 * 作者：created by albert on 2019-11-05 13:45
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class VideoActivity extends Activity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVideoView = findViewById(R.id.videoView);
        //  设置视频地址和标题 并开始播放
        mVideoView.setUp("http://vfx.mtime.cn/Video/2018/06/27/mp4/180627094726195356.mp4");
        mVideoView.setControlPanel(new ControlPanel(this));
        mVideoView.start();
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
