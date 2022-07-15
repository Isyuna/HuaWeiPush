package com.laozhang.project.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.laozhang.project.MainApi;
import com.laozhang.project.main.model.RecommendModel;
import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.RetrofitManager;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/14  10:53.
 * description :
 **/
public class ArticleRequest {

    public MutableLiveData<List<RecommendModel>> consumers = new MutableLiveData<>();


    public LiveData<ApiResponse<List<RecommendModel>>> getConsumerList(){
        return RetrofitManager.getInstance().create(MainApi.class).getRecommendText();
    }
}
