package com.zhy.login.type;

import androidx.lifecycle.LiveData;

import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.BingImg;

/**
 * author : zhangyun.
 * date  : 2022/3/21  16:49.
 * description :
 **/
public abstract class Login {

    public abstract LiveData<BingImg> login(String account, String password);
    public void loginByFace(String image ,LoginByFaceCallBack loginByFaceCallBack){

    }
    public  void loginByMsg(String msg,LoginCallBack loginCallBack){

    }
}
