package com.lv.libhttp.factory;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 作者：created by albert on 2018/9/6 11:28
 * 邮箱：lvzhongdi@icloud.com
 * 拦截器，在请求头中添加请求拦截
 **/
public class HttpCommonInterceptor implements Interceptor {

    private Map<String, String> mHeaderParamsMap = new HashMap<>();

    public HttpCommonInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder newRequest = request.newBuilder();
        newRequest.method(request.method(), request.body());
        Log.e("lv", request.url().toString());
        //添加新的公共参数到Head中
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                newRequest.header(params.getKey(), params.getValue());
            }
        }
        Request new_newRequest = newRequest.build();
        Response response = chain.proceed(new_newRequest);
        String responseString = response.body().string();

        Log.e("lv", "intercept: " + responseString);

        return response
                .newBuilder()
                .body(ResponseBody.create(response.body().contentType(), responseString))
                .build();
    }


    public static class Builder {
        HttpCommonInterceptor mHttpCommonInterceptor;

        public Builder() {
            mHttpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HttpCommonInterceptor build() {
            return mHttpCommonInterceptor;
        }

    }


}