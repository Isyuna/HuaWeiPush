package com.zhy.common.utils;

import android.content.Context;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

/**
 * author : zhangyun.
 * date  : 2022/4/12  15:17.
 * description : 继承自DefaultLifecycleObserver
 * 可自动托管LocationService生命周期 不用刻意去处理
 **/
public class LocationService implements DefaultLifecycleObserver {


    public  static  LocationService sLocationService;

    MediatorLiveData<String> location = new MediatorLiveData<>();

    public static LocationService getInstance(){
        if(sLocationService == null){
            sLocationService = new LocationService();
        }
        return sLocationService;
    }


    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        onDestroyLocation();
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        onStartLocation();
    }

    /**
     * 启动activity后做一些初始化工作
     */
    private   void onStartLocation(){
        System.out.print("onStartLocation");

    }

    /**
     * 获取当前定位
     */
    public LiveData<String> getLocation(){
        //一共3秒，每隔3秒执行一次
        CountDownTimer timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //还剩下多少秒millisUntilFinished/1000，依次为2、1、0

            }

            @Override
            public void onFinish() {//结束后的操作
                location.setValue("获取定位成功");
            }
        }.start();
        return location;
    }


    private void onDestroyLocation(){
        System.out.print("onDestroyLocation");
    }

}
