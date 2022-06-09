package com.zhy.login.type;

import androidx.lifecycle.LiveData;

import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.BingImg;
import com.zhy.login.LoginRequest;

/**
 * author : zhangyun.
 * date  : 2022/3/21  16:50.
 * description :
 **/
public class AccountLogin extends Login {
    LoginRequest loginRequest = new LoginRequest();

    @Override
    public LiveData<BingImg> login(String account, String password) {
        return loginRequest.requestLogin();
    }
}
