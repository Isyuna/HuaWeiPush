package com.mcp.设计模式.工厂模式.抽象工厂模式;

/**
 * author : zhangyun.
 * date  : 2022/3/1  15:41.
 * description :
 **/
public class PhoneCheckTime implements TimeInterface{
    @Override
    public void toCheckTime() {
        System.out.println("检查手机号登录时间效验");
    }
}
