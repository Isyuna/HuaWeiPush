package com.laozhang.project.request;

import androidx.lifecycle.LiveData;

import com.laozhang.project.MainApi;
import com.laozhang.project.main.model.CommentModel;
import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.RetrofitManager;

import java.util.List;

/**
 * author : zhangyun.
 * date  : 2022/7/27  15:05.
 * description :
 **/
public class CommentRequest {


    public LiveData<ApiResponse<List<CommentModel>>> getConsumerList(){
        return RetrofitManager.getInstance().create(MainApi.class).getComment();
    }
}
