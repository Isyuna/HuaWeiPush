package com.zhy.login.type;

/**
 * author : zhangyun.
 * date  : 2022/3/21  16:47.
 * description :
 **/
public interface LoginCallBack {
    void success(String data);
    void error(String error);
}
