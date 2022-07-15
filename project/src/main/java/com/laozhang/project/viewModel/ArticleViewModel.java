package com.laozhang.project.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.laozhang.project.main.model.RecommendModel;
import com.laozhang.project.request.ArticleRequest;
import com.laozhang.project.request.RecommendRequest;
import com.zhy.common.net.ApiResponse;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/14  10:52.
 * description :
 **/
public class ArticleViewModel extends AndroidViewModel {
    public ArticleViewModel(@NonNull Application application) {
        super(application);
    }
    public ArticleRequest request = new ArticleRequest();


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
