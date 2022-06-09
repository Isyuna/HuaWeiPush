package com.zhy.common.net;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * author : zhangyun.
 * date  : 2022/4/20  15:16.
 * description :
 **/
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    private static final String TAG = LiveDataCallAdapterFactory.class.getSimpleName();

    @Nullable
    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations,
                                 @NonNull Retrofit retrofit) {
        if (getRawType(returnType) != LiveData.class){
            return null;
        }
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawType = getRawType(observableType);
        Log.d(TAG, "get: rawType="+ rawType.getSimpleName());
        return new LiveDataCallAdapter<>(observableType);
    }
}
