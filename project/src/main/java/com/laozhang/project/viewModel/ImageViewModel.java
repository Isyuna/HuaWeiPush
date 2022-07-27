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
 * date  : 2022/7/15  11:40.
 * description :
 **/
public class ImageViewModel extends AndroidViewModel {
    public RecommendRequest request = new RecommendRequest();

    public ImageViewModel(@NonNull Application application) {
        super(application);
    }

    //刷新数据
    public LiveData<ApiResponse<List<RecommendModel>>> refreshData() {
        return  request.getRecommendImage();

    }

    //加载数据
    public LiveData<ApiResponse<List<RecommendModel>>> loadData() {
        return request.getConsumerList();
    }

    //获取数据库数据
    public void getDbData() {

    }
}
