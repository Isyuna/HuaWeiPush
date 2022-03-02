package com.mcp.设计模式.观察者模式;

import java.util.Observable;

/**
 * author : zhangyun.
 * date  : 2022/3/2  15:01.
 * description :
 **/
public class StoreSubject extends Subject{

    public void tellOther(Object msg){
        this.nodifObservers(null,msg);
    }


}
