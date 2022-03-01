package com.mcp.设计模式.工厂模式.简单工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  13:53.
 * description : 负责管理绘画的工厂类
 **/
public class ShapeFactory {


    public ShapeFactory() {

    }

    public static Shape getShape(Type type) {
        Shape shape = null;

        if (type == Type.APPLE) {
            shape = new AppleShape();
        }
        if (type == Type.CIRCLE) {
            shape = new CircleShape();
        }

        return shape;
    }
}
