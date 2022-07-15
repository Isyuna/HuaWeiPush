package com.laozhang.project.request;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.laozhang.project.MainApi;
import com.laozhang.project.main.model.ConsumerModel;
import com.zhy.common.net.Api;
import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.RetrofitManager;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/7  17:01.
 * description :
 **/
public class FollowRequest implements DefaultLifecycleObserver {

    public MutableLiveData<List<ConsumerModel>> consumers = new MutableLiveData<>();


    public LiveData<ApiResponse<List<ConsumerModel>>> getConsumerList(){
        return RetrofitManager.getInstance().create(MainApi.class).getConsumer();
    }


}
