package com.zhy.login.type;

import androidx.lifecycle.LiveData;

import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.BingImg;
import com.zhy.login.type.Login;
import com.zhy.login.type.LoginCallBack;

/**
 * author : zhangyun.
 * date  : 2022/3/23  10:26.
 * description : 模拟验证码登录
 **/
public class MsgLogin extends Login {
    @Override
    public LiveData<BingImg> login(String account, String password) {
        return null;
    }

    @Override
    public void loginByMsg(String msg, LoginCallBack loginCallBack) {
        super.loginByMsg(msg, loginCallBack);
    }
}
