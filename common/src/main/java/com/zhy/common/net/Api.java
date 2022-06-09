package com.zhy.common.net;

import androidx.lifecycle.LiveData;

import retrofit2.http.GET;

/**
 * author : zhangyun.
 * date  : 2022/4/20  15:34.
 * description :
 **/
public interface Api {

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    LiveData<BingImg> getBingImgLiveData();
}
