package com.laozhang.project.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.request.RecommendRequest;
import com.zhy.common.net.ApiResponse;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/13  09:50.
 * description :
 **/
public class RecommendViewModel extends AndroidViewModel {


    public RecommendRequest request = new RecommendRequest();

    public RecommendViewModel(@NonNull Application application) {
        super(application);
    }

    //刷新数据
    public LiveData<ApiResponse<List<RecommendModel>>> refreshData() {
        return  request.getConsumerList();

    }

    //加载数据
    public LiveData<ApiResponse<List<RecommendModel>>> loadData() {
       return request.getConsumerList();
    }

    //获取数据库数据
    public void getDbData() {

    }
}
