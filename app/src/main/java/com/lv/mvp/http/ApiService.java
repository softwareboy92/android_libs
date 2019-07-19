package com.lv.mvp.http;

import com.lv.mvp.model.HotCoinsResponse;
import com.lv.mvp.model.UpdateResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 作者：created by albert on 2019-07-18 13:37
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public interface ApiService {
    /**
     * 升级
     */
    @GET("/app/version/")
    Observable<UpdateResponse> getAppVersion();

    /**
     * 获取热门币种类
     *
     * @return
     */
    @GET("/wallet/hot")
    Observable<HotCoinsResponse> getHotCoins();

}
