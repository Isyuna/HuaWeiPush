package com.mcp.设计模式.工厂模式.简单工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  11:23.
 * description : 简单工厂模式 用于处理少量对象，且客户端不太关注创建过程的场景下使用
 **/
public class Client {


    public static void main(String[] arg){

      Shape shape =  ShapeFactory.getShape(Type.APPLE);
      shape.draw();
    }
}
