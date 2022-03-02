package com.mcp.设计模式.观察者模式;

import java.util.Observable;
import java.util.Observer;

/**
 * author : zhangyun.
 * date  : 2022/3/2  14:42.
 * description : 零食观察者
 **/
public class SnacksObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("零食店收到通知:"+arg+"，随时就位");
    }
}
