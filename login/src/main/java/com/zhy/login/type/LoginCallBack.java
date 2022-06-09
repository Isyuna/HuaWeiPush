package com.zhy.login.type;

import androidx.lifecycle.LiveData;

import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.BingImg;

/**
 * author : zhangyun.
 * date  : 2022/3/21  16:47.
 * description :
 **/
public interface LoginCallBack {
    void success(LiveData<ApiResponse<BingImg>> data);
    void error(String error);
}
