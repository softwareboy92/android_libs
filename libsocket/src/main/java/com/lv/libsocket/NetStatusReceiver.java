package com.lv.libsocket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import wy.experiment.xposed.logic.config.MonitorApplication;
import wy.experiment.xposed.logic.util.CommSharedUtil;

public class NetStatusReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {

            // 获取网络连接管理器
            ConnectivityManager connectivityManager
                = (ConnectivityManager) MonitorApplication.getmApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取当前网络状态信息
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();

            if (info != null && info.isAvailable()) {
                Log.i("WsManager", "onReceive: 监听到可用网络切换,调用重连方法");
                String token = CommSharedUtil.getInstance(context).getString("token");
                WsManager.getInstance(context,token).reconnect();//wify 4g切换重连websocket
            }

        }
    }
}