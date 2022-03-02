package com.mcp.设计模式.观察者模式;

import java.util.Observable;

/**
 * author : zhangyun.
 * date  : 2022/3/2  15:18.
 * description :
 **/
public class Store2Observable extends Observable {


    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    public void setMsg(String msg){
        setChanged();
        notifyObservers(msg);
    }
}
