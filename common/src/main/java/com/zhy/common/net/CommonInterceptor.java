package com.zhy.common.net;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author : zhangyun.
 * date  : 2022/7/7  16:49.
 * description : 添加公共参数
 **/
public class CommonInterceptor implements Interceptor {
    private final String uk;
    private final String channel;
    private final String app;
    private final String device;

    public CommonInterceptor(String uk, String channel, String app,  String device) {
        this.uk = uk;
        this.channel = channel;
        this.app = app;
        this.device = device;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request oldRequest = chain.request();
        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())
                .addQueryParameter("uk", uk)
                .addQueryParameter("channel", channel)
                .addQueryParameter("app", app)
                .addQueryParameter("device", device);

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }
}
