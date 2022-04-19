package com.zhy.login.type;

/**
 * author : zhangyun.
 * date  : 2022/3/21  18:10.
 * description :
 **/
public interface LoginByFaceCallBack {
    void success(String data);
    void error(String error);
}
