package com.mcp;

import android.app.Application;

import com.zhy.common.net.RetrofitManager;

/**
 * author : zhangyun.
 * date  : 2022/6/10  09:47.
 * description :
 **/
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance().init(this);
    }
}
