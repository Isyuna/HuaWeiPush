package com.zhy.login;

/**
 * author : zhangyun.
 * date  : 2022/3/21  18:12.
 * description :
 **/
public class LoginFactory {


    public Login createLogin(LoginType type){
        if(type == LoginType.ACCOUNT){
            return new AccountLogin();
        }else if(type == LoginType.FACE){
            return new FaceLogin();
        }else if(type == LoginType.MSG){
            return new MsgLogin();
        }
        return null;
    }
}
