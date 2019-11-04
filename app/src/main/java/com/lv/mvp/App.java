package com.lv.mvp;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.fm.openinstall.OpenInstall;
import com.lv.libmvp.languageutils.MultiLanguageUtil;

/**
 * 作者：created by albert on 2019-07-18 13:26
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiLanguageUtil.init(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        builder.detectFileUriExposure();
        StrictMode.setVmPolicy(builder.build());
        if (isMainProcess()) {
            OpenInstall.init(this);
        }
    }


    public boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }
}
