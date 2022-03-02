package com.mcp.设计模式.观察者模式;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * author : zhangyun.
 * date  : 2022/3/2  14:43.
 * description :
 **/
public class DrinksObserver implements Observer {
    CallBack callBack;


    public DrinksObserver(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("饮料店收到通知:"+arg+"，随时就位");
        if(callBack != null){
            callBack.msgCallBack("饮料店已发货");

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
        }
    }

}
