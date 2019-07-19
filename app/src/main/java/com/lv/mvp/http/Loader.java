package com.lv.mvp.http;

import com.lv.libhttp.factory.RetrofitFactory;
import com.lv.libhttp.loader.ObjectLoader;
import com.lv.libhttp.utils.AndroidScheduler;
import com.lv.mvp.model.HotCoinsResponse;
import com.lv.mvp.model.UpdateResponse;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：created by albert on 2019-07-18 13:54
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class Loader extends ObjectLoader implements ApiService {

    private ApiService mApiService;
    private static Loader mLoader;

    public static Loader getLoader() {
        if (mLoader==null){
            mLoader = new Loader();
        }
        return mLoader;
    }

    public Loader() {
        if (mApiService == null)
            mApiService = RetrofitFactory.getFactory().createService(ApiService.class);
    }

    public Observable<UpdateResponse> getAppVersion() {
        return mApiService.getAppVersion()
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidScheduler.mainThread());
    }

    @Override
    public Observable<HotCoinsResponse> getHotCoins() {
        return mApiService.getHotCoins()
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidScheduler.mainThread());
    }


}
