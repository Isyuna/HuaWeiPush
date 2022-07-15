package com.laozhang.project.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.laozhang.project.main.model.ConsumerModel;
import com.laozhang.project.request.FollowRequest;
import com.zhy.common.net.ApiResponse;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/1  17:05.
 * description :
 **/
public class FollowViewModel extends AndroidViewModel {


    public MutableLiveData<List<ConsumerModel>> consumerData = new MutableLiveData<>();
    public final FollowRequest followRequest = new FollowRequest();
    public FollowViewModel(@NonNull Application application) {
        super(application);
    }


}
