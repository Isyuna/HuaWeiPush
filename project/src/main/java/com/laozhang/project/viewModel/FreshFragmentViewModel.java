package com.laozhang.project.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.request.RecommendRequest;
import com.zhy.common.net.ApiResponse;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/15  11:21.
 * description :
 **/
public class FreshFragmentViewModel extends AndroidViewModel {

    public RecommendRequest request = new RecommendRequest();

    public FreshFragmentViewModel(@NonNull Application application) {
        super(application);
    }


    //刷新数据
    public LiveData<ApiResponse<List<RecommendModel>>> refreshData() {
        return  request.getRecommendFresh();

    }

    //加载数据
    public LiveData<ApiResponse<List<RecommendModel>>> loadData() {
        return request.getRecommendFresh();
    }

}
