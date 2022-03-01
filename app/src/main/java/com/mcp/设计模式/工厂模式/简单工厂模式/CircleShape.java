package com.mcp.设计模式.工厂模式.简单工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  11:26.
 * description :
 **/
public class CircleShape implements Shape{
    @Override
    public void draw() {
        System.out.println("画一个圆形");
    }
}
