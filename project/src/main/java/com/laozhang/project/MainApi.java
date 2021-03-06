package com.laozhang.project;

import androidx.lifecycle.LiveData;

import com.laozhang.project.main.model.CommentModel;
import com.laozhang.project.main.model.ConsumerModel;
import com.laozhang.project.main.model.RecommendModel;
import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.BingImg;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author : zhangyun.
 * date  : 2022/7/7  17:02.
 * description :
 **/
public interface MainApi {

    @POST("home/attention/recommend")
    LiveData<ApiResponse<List<ConsumerModel>>> getConsumer();

    @POST("home/recommend")
    LiveData<ApiResponse<List<RecommendModel>>> getRecommend();

    @POST("home/text")
    LiveData<ApiResponse<List<RecommendModel>>> getRecommendText();

    @POST("douyin/list")
    LiveData<ApiResponse<List<RecommendModel>>> getRecommendFresh();

    @POST("home/pic")
    LiveData<ApiResponse<List<RecommendModel>>> getRecommendImage();

    @POST("/jokes/comment/list")
    LiveData<ApiResponse<List<CommentModel>>> getComment();
}
