package com.zhy.login.type;

import androidx.lifecycle.LiveData;

import com.zhy.common.net.ApiResponse;
import com.zhy.common.net.BingImg;

/**
 * author : zhangyun.
 * date  : 2022/3/21  18:18.
 * description :
 **/
public class FaceLogin extends Login{

    @Override
    public LiveData<BingImg> login(String account, String password) {
        return null;
    }

    @Override
    public void loginByFace(String image, LoginByFaceCallBack loginByFaceCallBack) {
//        login("account", "password", new LoginCallBack() {
//            @Override
//            public void success(String data) {
//                loginByFaceCallBack.success("成功");
//
//            }
//
//            @Override
//            public void error(String error) {
//                loginByFaceCallBack.error("识别");
//
//            }
//        });
    }

}
