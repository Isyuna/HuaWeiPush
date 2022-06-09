package com.zhy.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.zhy.common.net.Api;
import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.BingImg;
import com.zhy.common.net.RetrofitManager;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * author : zhangyun.
 * date  : 2022/4/20  15:37.
 * description :
 **/
public class LoginRequest implements DefaultLifecycleObserver {

    public LiveData<BingImg> requestLogin() {
        return RetrofitManager.getInstance().create(Api.class).getBingImgLiveData();
    }

}
