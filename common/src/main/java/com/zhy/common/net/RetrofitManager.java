package com.zhy.common.net;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zhy.common.utils.DeviceUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : zhangyun.
 * date  : 2022/4/20  15:20.
 * description :
 **/
public class RetrofitManager {

    private static final String TAG ="RetrofitManager" ;
    private static final int DEFAULT_CONNECT_TIME = 30;
    private static final int DEFAULT_WRITE_TIME = 30;
    private static final int DEFAULT_READ_TIME = 30;
    private static final String BASE_URL = "http://tools.cretinzp.com/jokes/";
    private final Retrofit retrofit;

    private Context context;
    private static class SingletonHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public void init(Context context){
        this.context = context;
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
        OkHttpClient.Builder sOkHttpBuilder = new OkHttpClient.Builder();
        sOkHttpBuilder.addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Log.d(TAG,"app:"+String.valueOf(DeviceUtils.getVersion(context))
                +"device:"+DeviceUtils.getSystemModel());
                Request request = original.newBuilder()
                        .header("project_token", "B71900149CB446DFA953B6E74EBCB6D6")
                        .header("uk", "12313123123123123123123123123")
                        .header("channel", "cretin_open_api")
                        .header("app", String.valueOf(DeviceUtils.getVersion(context)))
                        .header("device", DeviceUtils.getSystemModel())
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        //超时时长
        sOkHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(sOkHttpBuilder.build())//设置使用okhttp网络请求
                .baseUrl(BASE_URL)//设置服务器路径
                .addConverterFactory(GsonConverterFactory.create())//添加转化库，默认是Gson
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();

    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }


}
