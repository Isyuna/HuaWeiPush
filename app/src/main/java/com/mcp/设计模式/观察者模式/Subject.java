package com.mcp.设计模式.观察者模式;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * author : zhangyun.
 * date  : 2022/3/2  14:47.
 * description :
 **/
public abstract class Subject {
    /**
     * 用来保存注册的观察者对象
     */
    private List<Observer> list = new ArrayList<Observer>();
    /**
     * 注册观察者对象
     * @param observer    观察者对象
     */
    public void attach(Observer observer){

        list.add(observer);
        System.out.println("Attached an observer");
    }
    /**
     * 删除观察者对象
     * @param observer    观察者对象
     */
    public void detach(Observer observer){

        list.remove(observer);
    }
    /**
     * 通知所有注册的观察者对象
     */
    public void nodifObservers(Observable o, Object arg){

        for(Observer observer : list){
            observer.update(o,arg);
        }
    }
}
