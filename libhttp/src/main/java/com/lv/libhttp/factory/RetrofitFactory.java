package com.lv.libhttp.factory;


import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lvzhongdi
 * on 2018/6/26.
 * Email: lvzhongdi@icloud.com
 */
public class RetrofitFactory {

    private static RetrofitFactory mFactory;
    private Retrofit mRetrofit;

    private RetrofitFactory(String url) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);//连接失败后重新连接
        builder.proxy(Proxy.NO_PROXY);//设置不使用代理，禁止抓包获取App数据

        HttpCommonInterceptor.Builder interceptor = new HttpCommonInterceptor.Builder();
        interceptor.addHeaderParams("Content-Type", "application/json");

        builder.addInterceptor(interceptor.build());

        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitFactory getFactory(String url) {
        synchronized (RetrofitFactory.class) {
            if (mFactory == null)
                mFactory = new RetrofitFactory(url);
        }
        return mFactory;
    }

    public <T> T createService(Class<T> service) {
        return mRetrofit.create(service);
    }
}
