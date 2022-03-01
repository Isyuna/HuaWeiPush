package com.mcp.设计模式.工厂模式.抽象工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  15:43.
 * description :
 **/
public interface LoginFactory {

    TimeInterface getTimeInterface();

    LoginInterface getLoginInterFace();
}
