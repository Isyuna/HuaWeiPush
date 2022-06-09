package com.zhy.common.net;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : zhangyun.
 * date  : 2022/4/20  15:20.
 * description :
 **/
public class RetrofitManager {

    private static final String TAG ="APIServer" ;
    private static final int DEFAULT_CONNECT_TIME = 30;
    private static final int DEFAULT_WRITE_TIME = 30;
    private static final int DEFAULT_READ_TIME = 30;
    private final Retrofit retrofit;

    private static class SingletonHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    /**
     * 获取RetrofitServiceManager
     *
     * @return
     */
    public static RetrofitManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private RetrofitManager() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                Log.d(TAG,"APIServer_Logger = "+s);
            }
        });
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder= new OkHttpClient.Builder();

        OkHttpClient.Builder sOkHttpBuilder = new OkHttpClient.Builder();
        //超时时长
        sOkHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(sOkHttpBuilder.build())//设置使用okhttp网络请求
                .baseUrl("https://cn.bing.com/")//设置服务器路径
                .addConverterFactory(GsonConverterFactory.create())//添加转化库，默认是Gson
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();

    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }


}
