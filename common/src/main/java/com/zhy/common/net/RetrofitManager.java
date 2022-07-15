package com.zhy.common.net;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zhy.common.utils.DeviceUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
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
    private static volatile RetrofitManager helper;
    private Context context;

    public static RetrofitManager getInstance() {
        if (helper == null) {
            synchronized (RetrofitManager.class) {
                if (helper == null) {
                    helper = new RetrofitManager();
                }
            }
        }
        return helper;
    }
    public void init(Application context){
        helper.context = context;
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
//                Log.d(TAG,"app:"+String.valueOf(DeviceUtils.getVersion(context))
//                +"device:"+DeviceUtils.getSystemModel());
                Request request = original.newBuilder()
                        .header("project_token", "B71900149CB446DFA953B6E74EBCB6D6")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        sOkHttpBuilder.addInterceptor(new CommonInterceptor("12313123123123123123123123123","cretin_open_api"
                ,"1.0","HUAWEI"));
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
