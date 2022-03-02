package com.mcp.设计模式.观察者模式;

import java.util.Observable;

/**
 * author : zhangyun.
 * date  : 2022/3/2  10:44.
 * description : 观察者模式 一个对象被订阅 当对象状态发生改变时所有订阅的都发生改变，即一人发布，多人订阅
 * 事件多级触发场景；
 * 当一个对象必须通知别的对象，而它又不能假定对象是谁；
 * 跨系统的消息交换场景，如消息队列、事件总线的处理机制。
 * 例：商店需要向各个进货商进货，以水果、零食、饮料为例
 **/
public class Client {
    public static void main(String[] args) {

        StoreSubject subject = new StoreSubject();

        FruitsObserver fruitsObserver = new FruitsObserver();

        SnacksObserver snacksObserver = new SnacksObserver();

        DrinksObserver drinksObserver = new DrinksObserver(new CallBack() {
            @Override
            public void msgCallBack(String msgCallback) {
                System.out.println("商家收到通知："+msgCallback);
            }
        });

        subject.attach(fruitsObserver);
        subject.attach(snacksObserver);
        subject.attach(drinksObserver);

        subject.tellOther( "进货");

        System.out.println("****************");
        Store2Observable observable = new Store2Observable();

        observable.addObserver(fruitsObserver);
        observable.addObserver(snacksObserver);
        observable.addObserver(drinksObserver);
        observable.setMsg("商店2 收到假货");


    }
}
