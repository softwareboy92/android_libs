package com.lv.mvp;

import android.app.Application;
import android.os.StrictMode;

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
    }
}
