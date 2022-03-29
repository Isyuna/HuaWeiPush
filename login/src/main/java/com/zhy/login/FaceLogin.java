package com.zhy.login;

/**
 * author : zhangyun.
 * date  : 2022/3/21  18:18.
 * description :
 **/
public class FaceLogin extends Login{
    @Override
    protected void loginByFace(String image, LoginByFaceCallBack loginByFaceCallBack) {
        login("account", "password", new LoginCallBack() {
            @Override
            public void success(String data) {
                loginByFaceCallBack.success("成功");

            }

            @Override
            public void error(String error) {
                loginByFaceCallBack.error("识别");

            }
        });
    }

}
