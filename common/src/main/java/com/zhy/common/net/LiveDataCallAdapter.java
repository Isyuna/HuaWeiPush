package com.zhy.common.net;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * author : zhangyun.
 * date  : 2022/4/20  15:03.
 * description :
 **/
public class LiveDataCallAdapter<T> implements CallAdapter<T, LiveData<T>> {

    private Type mResponseType;

    LiveDataCallAdapter(Type mResponseType) {
        this.mResponseType = mResponseType;
    }


    /**
     * 用于返回从GSON数据到JAVA对象的类型
     * @return
     */
    @NonNull
    @Override
    public Type responseType() {
        return mResponseType;
    }

    /**
     * 该方法用于将 Retrofit 请求返回时的 Call 对象转换为需要的类，这里为我们自定义的
     * LiveData 对象，为了后续监听网络回调，这里 CallAdapterFactory 构建 CallAdapter
     * 时需要传递当前 Call 实例
     * @param call
     * @return
     */
    @NonNull
    @Override
    public LiveData<T> adapt(@NonNull final Call<T> call) {
        return new ResponseLiveData<>(call);
    }


    private static class ResponseLiveData<T> extends LiveData<T> {

        private final AtomicBoolean stared = new AtomicBoolean(false);
        private final Call<T> call;

        public ResponseLiveData(Call<T> call) {
            this.call = call;
        }

        /**
         * 该方法在 LiveData 实例的 observer 由 0变1 时会调用，我们传递进来的 Call 在这里使用。
         *
         * 由于网络线程在后台运行，此时应该对 Call 实例 enqueue 一个 Callback，Callback 重写的 onResponse()
         * 方法中对 LiveData<T> 进行 postValue\<T>()
         */
        @Override
        protected void onActive() {
            super.onActive();
            if (stared.compareAndSet(false, true)) {
                call.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                        T body = response.body();
                        postValue(body);
                    }

                    @Override
                    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                        postValue(null);
                    }
                });
            }

        }
    }


}
