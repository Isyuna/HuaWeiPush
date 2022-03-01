package com.mcp.设计模式.工厂模式.工厂方法模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  14:32.
 * description :
 **/
public class JpgReader implements Reader{
    @Override
    public void read() {
        System.out.println("JpgReader");
    }
}
