package com.zhy.login.type;

/**
 * author : zhangyun.
 * date  : 2022/3/21  16:50.
 * description :
 **/
public class AccountLogin extends Login{
    @Override
    public void login(String account, String password, LoginCallBack loginCallBack) {
        loginCallBack.success("登录成功");
    }
}
