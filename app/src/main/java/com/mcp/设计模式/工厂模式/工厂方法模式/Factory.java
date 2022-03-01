package com.mcp.设计模式.工厂模式.工厂方法模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  14:34.
 * description :
 **/
public class Factory {
    Reader reader;

    public Factory(Reader reader) {
        this.reader = reader;
    }
    public void draw(){
        if(reader != null){
            reader.read();
        }
    }
}
