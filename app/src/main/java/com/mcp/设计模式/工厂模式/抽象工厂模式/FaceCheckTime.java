package com.mcp.设计模式.工厂模式.抽象工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  15:42.
 * description :
 **/
public class FaceCheckTime implements TimeInterface{
    @Override
    public void toCheckTime() {
        System.out.println("效验人脸登录时间");
    }
}
